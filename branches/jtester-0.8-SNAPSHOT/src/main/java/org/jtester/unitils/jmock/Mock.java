package org.jtester.unitils.jmock;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
public @interface Mock {
	String[] injectInto() default {};

	String[] byProperty() default {};

	String value() default "";
}
