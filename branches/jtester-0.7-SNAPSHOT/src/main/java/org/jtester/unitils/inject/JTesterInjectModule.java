package org.jtester.unitils.inject;

import static org.unitils.util.AnnotationUtils.getFieldsAnnotatedWith;
import static org.unitils.util.ModuleUtils.getEnumValueReplaceDefault;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jtester.utility.ReflectUtil;
import org.springframework.aop.framework.Advised;
import org.unitils.core.UnitilsException;
import org.unitils.inject.InjectModule;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.inject.util.InjectionUtils;
import org.unitils.inject.util.PropertyAccess;

public class JTesterInjectModule extends InjectModule {
	@Override
	public void injectObjects(Object test) {
		super.injectObjects(test);
		injectInjectedMock(test);
	}

	public void injectInjectedMock(Object test) {
		Set<Field> fields = getFieldsAnnotatedWith(test.getClass(), InjectedMock.class);
		for (Field field : fields) {
			InjectedMock mock = field.getAnnotation(InjectedMock.class);
			String ognl = mock.property();
			if (StringUtils.isEmpty(ognl)) {
				this.injectMock(test, field, mock);// @Mock + @InjectIntoByType
			} else {
				this.injectMock(test, field, mock, ognl);// @Mock + @InjectInto
			}
		}
	}

	protected void injectMock(Object test, Field fieldToInject, InjectedMock mock) {
		Object objectToInject = getObjectToInject(test, fieldToInject);
		Class<?> objectToInjectType = getObjectToInjectType(test, fieldToInject);
		PropertyAccess propertyAccess = PropertyAccess.DEFAULT;

		List<Object> targets = getTargets(InjectedMock.class, fieldToInject, mock.target(), test);
		if (targets.size() == 0) {
			throw new UnitilsException(getSituatedErrorMessage(InjectedMock.class, fieldToInject,
					"The target should either be "
							+ "specified explicitly using the target property, or by using the @"
							+ TestedObject.class.getSimpleName() + " annotation"));
		}

		for (Object target : targets) {
			try {
				InjectionUtils.injectIntoByType(objectToInject, objectToInjectType, target(target), propertyAccess);
			} catch (UnitilsException e) {
				throw new UnitilsException(getSituatedErrorMessage(InjectedMock.class, fieldToInject, e.getMessage()),
						e);
			}
		}
	}

	protected void injectMock(Object test, Field fieldToInject, InjectedMock mock, String ognl) {
		Object objectToInject = getObjectToInject(test, fieldToInject);

		List<Object> targets = getTargets(InjectedMock.class, fieldToInject, mock.target(), test);
		if (targets.size() == 0) {
			throw new UnitilsException(getSituatedErrorMessage(InjectedMock.class, fieldToInject,
					"The target should either be "
							+ "specified explicitly using the target property, or by using the @"
							+ TestedObject.class.getSimpleName() + " annotation"));
		}

		for (Object target : targets) {
			try {
				InjectionUtils.injectInto(objectToInject, target(target), ognl);
			} catch (UnitilsException e) {
				throw new UnitilsException(getSituatedErrorMessage(InjectedMock.class, fieldToInject, e.getMessage()),
						e);
			}
		}
	}

	@Override
	protected void injectByType(Object test, Field fieldToInject) {
		InjectIntoByType injectIntoByTypeAnnotation = fieldToInject.getAnnotation(InjectIntoByType.class);

		Object objectToInject = getObjectToInject(test, fieldToInject);
		Class<?> objectToInjectType = getObjectToInjectType(test, fieldToInject);
		PropertyAccess propertyAccess = getEnumValueReplaceDefault(InjectIntoByType.class, "propertyAccess",
				injectIntoByTypeAnnotation.propertyAccess(), defaultAnnotationPropertyValues());

		List<Object> targets = getTargets(InjectIntoByType.class, fieldToInject, injectIntoByTypeAnnotation.target(),
				test);
		if (targets.size() == 0) {
			throw new UnitilsException(getSituatedErrorMessage(InjectIntoByType.class, fieldToInject,
					"The target should either be "
							+ "specified explicitly using the target property, or by using the @"
							+ TestedObject.class.getSimpleName() + " annotation"));
		}

		for (Object target : targets) {
			try {
				InjectionUtils.injectIntoByType(objectToInject, objectToInjectType, target(target), propertyAccess);
			} catch (UnitilsException e) {
				throw new UnitilsException(getSituatedErrorMessage(InjectIntoByType.class, fieldToInject, e
						.getMessage()), e);
			}
		}
	}
	
	@Override
    protected void inject(Object test, Field fieldToInject) {
        InjectInto injectIntoAnnotation = fieldToInject.getAnnotation(InjectInto.class);

        String ognlExpression = injectIntoAnnotation.property();
        if (StringUtils.isEmpty(ognlExpression)) {
            throw new UnitilsException(getSituatedErrorMessage(InjectInto.class, fieldToInject, "Property cannot be empty"));
        }
        Object objectToInject = getObjectToInject(test, fieldToInject);

        List<Object> targets = getTargets(InjectInto.class, fieldToInject, injectIntoAnnotation.target(), test);
        if (targets.size() == 0) {
            throw new UnitilsException(getSituatedErrorMessage(InjectInto.class, fieldToInject, "The target should either be " +
                    "specified explicitly using the target property, or by using the @" + TestedObject.class.getSimpleName() +
                    " annotation"));
        }

        for (Object target : targets) {
            try {
                InjectionUtils.injectInto(objectToInject, target(target), ognlExpression);
            } catch (UnitilsException e) {
                throw new UnitilsException(getSituatedErrorMessage(InjectInto.class, fieldToInject, e.getMessage()), e);
            }
        }
    }

	@SuppressWarnings("unchecked")
	private Map<Class<? extends Annotation>, Map<String, String>> defaultAnnotationPropertyValues() {
		try {
			return (Map<Class<? extends Annotation>, Map<String, String>>) ReflectUtil.getFieldValue(
					InjectModule.class, this, "defaultAnnotationPropertyValues");
		} catch (Exception e) {
			throw new RuntimeException(e);
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
			throw new RuntimeException(e);
		}
	}
}
