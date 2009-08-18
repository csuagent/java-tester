package org.jtester.unitils.jmock;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
public @interface MockBean {
	String value() default "";

	String bean() default "";

	MockType type() default MockType.ByName;

	public static enum MockType {
		ByName;// , ByType;
	}
}
