package org.jtester.unitils.jmock;

import static org.unitils.util.AnnotationUtils.getFieldsAnnotatedWith;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

import org.jtester.exception.JTesterException;
import org.jtester.utility.ReflectUtil;
import org.jtester.utility.StringUtil;
import org.springframework.aop.framework.Advised;
import org.unitils.core.Module;
import org.unitils.core.TestListener;
import org.unitils.inject.util.InjectionUtils;
import org.unitils.inject.util.PropertyAccess;

public class InjectMockModule implements Module {

	public void afterInit() {
		;
	}

	public TestListener getTestListener() {
		return new InjectMockListener();
	}

	public void init(Properties configuration) {
		;
	}

	/**
	 * 创建MockBean对象
	 * 
	 * @param testedObject
	 */
	private void injectMock(Object testedObject) {
		Set<Field> mockFields = getFieldsAnnotatedWith(testedObject.getClass(), Mock.class);
		for (Field mockField : mockFields) {
			Class<?> injectedClazz = mockField.getType();
			Mock mock = mockField.getAnnotation(Mock.class);
			Object injectedObject = ReflectUtil.getFieldValue(testedObject, mockField);
			injectedInto(mock, testedObject, injectedClazz, injectedObject);
		}
	}

	private void injectedInto(Mock mock, Object testedObject, Class<?> injectedClazz, Object injectedObject) {
		String[] targets = mock.injectInto();
		String[] properties = mock.byProperty();
		for (int index = 0; index < targets.length; index++) {
			String target = targets[index];
			Object targetObject = ReflectUtil.getPropertyValue(testedObject, target);
			targetObject = target(targetObject);
			if (targetObject == null) {
				throw new RuntimeException("can't inject a mock object into a null object, ongl = " + target);
			}
			String property = index < properties.length ? properties[index] : null;
			if (StringUtil.isBlankOrNull(property)) {
				InjectionUtils.injectIntoByType(injectedObject, injectedClazz, targetObject, PropertyAccess.FIELD);
			} else {
				InjectionUtils.injectInto(injectedObject, targetObject, property);
			}
		}
	}

	public Object target(Object target) {
		try {
			if (target instanceof Advised) {
				return ((Advised) target).getTargetSource().getTarget();
			} else {
				return target;
			}
		} catch (Exception e) {
			throw new JTesterException(e);
		}
	}

	protected class InjectMockListener extends TestListener {
		@Override
		public void beforeTestMethod(Object testObject, Method testMethod) {
			injectMock(testObject);
		}
	}
}
