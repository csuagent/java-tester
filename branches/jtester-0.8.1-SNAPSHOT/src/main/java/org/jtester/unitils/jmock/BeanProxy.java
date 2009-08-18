package org.jtester.unitils.jmock;

import java.lang.reflect.Method;

import org.jmock.api.Imposteriser;
import org.jmock.api.Invocation;
import org.jmock.api.Invokable;
import org.jmock.lib.legacy.ClassImposteriser;

public class BeanProxy implements Invokable {
	private String name;

	private Class<?> type;

	protected BeanProxy(final String name, final Class<?> type) {
		this.name = name;
		this.type = type;
	}

	public Object invoke(Invocation invocation) throws Throwable {
		Object mock = MockBeanRegister.getBean(name, type);
		Method method = invocation.getInvokedMethod();
		return method.invoke(mock, invocation.getParametersAsArray());
	}

	private static Imposteriser imposteriser = ClassImposteriser.INSTANCE;

	@SuppressWarnings("unchecked")
	public static <T> T proxy(final String name, final Class<?> type) {
		return (T) imposteriser.imposterise(new BeanProxy(name, type), type);
	}
}
