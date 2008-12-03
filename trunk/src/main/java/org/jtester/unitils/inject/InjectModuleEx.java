package org.jtester.unitils.inject;

import static org.unitils.util.ModuleUtils.getEnumValueReplaceDefault;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.jtester.utility.ReflectUtil;
import org.springframework.aop.framework.Advised;
import org.unitils.core.UnitilsException;
import org.unitils.inject.InjectModule;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.inject.util.InjectionUtils;
import org.unitils.inject.util.PropertyAccess;

public class InjectModuleEx extends InjectModule {
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
				target = target(target);
				InjectionUtils.injectIntoByType(objectToInject, objectToInjectType, target, propertyAccess);
			} catch (UnitilsException e) {
				throw new UnitilsException(getSituatedErrorMessage(InjectIntoByType.class, fieldToInject, e
						.getMessage()), e);
			}
		}
	}

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
