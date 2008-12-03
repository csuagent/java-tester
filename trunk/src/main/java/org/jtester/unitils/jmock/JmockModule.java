package org.jtester.unitils.jmock;

import static org.unitils.util.AnnotationUtils.getFieldsAnnotatedWith;
import static org.unitils.util.ReflectionUtils.setFieldValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.jtester.unitils.inject.InjectedMock;
import org.unitils.core.Module;
import org.unitils.core.TestListener;

public class JmockModule implements Module {
	private Mockery context;

	public void afterInit() {
		;
	}

	public TestListener getTestListener() {
		return new JmockTestListener();
	}

	public void init(Properties configuration) {
		;
	}

	public Mockery getMockery() {
		return this.context;
	}

	private void createMocks(Object testObject) {
		Set<Field> mockFields = getFieldsAnnotatedWith(testObject.getClass(), Mock.class);
		for (Field mockField : mockFields) {

			Class<?> mockType = mockField.getType();
			Mock mock = mockField.getAnnotation(Mock.class);

			Object mockObject = null;
			if (mock.value() == null || "".equals(mock.value().trim())) {
				mockObject = context.mock(mockType);
			} else {
				mockObject = context.mock(mockType, mock.value());
			}

			setFieldValue(testObject, mockField, mockObject);
		}
	}

	private void createInjectedMocks(Object testObject) {
		Set<Field> mockFields = getFieldsAnnotatedWith(testObject.getClass(), InjectedMock.class);
		for (Field mockField : mockFields) {

			Class<?> mockType = mockField.getType();
			InjectedMock mock = mockField.getAnnotation(InjectedMock.class);

			Object mockObject = null;
			if (mock.value() == null || "".equals(mock.value().trim())) {
				mockObject = context.mock(mockType);
			} else {
				mockObject = context.mock(mockType, mock.value());
			}

			setFieldValue(testObject, mockField, mockObject);
		}
	}

	protected class JmockTestListener extends TestListener {
		@Override
		public void beforeTestSetUp(Object testObject, Method testMethod) {
			context = new Mockery() {
				{
					setImposteriser(ClassImposteriser.INSTANCE);
				}
			};
			createMocks(testObject);
			createInjectedMocks(testObject);
		}

		@Override
		public void afterTestMethod(Object testObject, Method testMethod, Throwable throwable) {
			context.assertIsSatisfied();
		}
	}
}
