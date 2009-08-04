package org.jtester.unitils.jmock;

import static org.unitils.util.AnnotationUtils.getFieldsAnnotatedWith;
import static org.unitils.util.ReflectionUtils.setFieldValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jmock.Mockery;
import org.jmock.api.MockObjectNamingScheme;
import org.jmock.lib.CamelCaseNamingScheme;
import org.jmock.lib.legacy.ClassImposteriser;
import org.jtester.unitils.inject.InjectedMock;
import org.unitils.core.Module;
import org.unitils.core.TestListener;

public class JmockModule implements Module {
	private Mockery context;

	private MockObjectNamingScheme namingScheme = CamelCaseNamingScheme.INSTANCE;

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

	private void createMocks(Object testedObject) {
		Set<Field> mockFields = getFieldsAnnotatedWith(testedObject.getClass(), Mock.class);
		for (Field mockField : mockFields) {
			Mock mock = mockField.getAnnotation(Mock.class);
			this.mock(testedObject, mock.value(), mockField);
		}

		Set<Field> mockBeansByName = getFieldsAnnotatedWith(testedObject.getClass(), MockBeanByName.class);

		for (Field mockField : mockBeansByName) {
			MockBeanByName mock = mockField.getAnnotation(MockBeanByName.class);
			Object mockObject = this.mock(testedObject, mock.value(), mockField);

			MockBeans.addMockBeanByName(mockField.getName(), mockObject);
		}
	}

	private Object mock(Object testedObject, String mockname, Field field) {
		Class<?> mockType = field.getType();
		if (StringUtils.isBlank(mockname)) {
			mockname = namingScheme.defaultNameFor(mockType);
		}
		mockname = mockname + "_" + Thread.currentThread().getId();
		Object mockObject = context.mock(mockType, mockname);

		setFieldValue(testedObject, field, mockObject);
		return mockObject;
	}

	private void createInjectedMocks(Object testObject) {
		Set<Field> mockFields = getFieldsAnnotatedWith(testObject.getClass(), InjectedMock.class);
		for (Field mockField : mockFields) {

			Class<?> mockType = mockField.getType();
			InjectedMock mock = mockField.getAnnotation(InjectedMock.class);

			Object mockObject = null;
			if (StringUtils.isBlank(mock.value())) {
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
