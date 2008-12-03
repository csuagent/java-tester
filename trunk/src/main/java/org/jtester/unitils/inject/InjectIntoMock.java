package org.jtester.unitils.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.unitils.inject.util.PropertyAccess;

@Target(FIELD)
@Retention(RUNTIME)
public @interface InjectIntoMock {
	String target() default "";

	String property() default "";

	PropertyAccess propertyAccess() default PropertyAccess.DEFAULT;
}
