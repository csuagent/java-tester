package org.jtester.unitils.jmock;

import org.jmock.Mockery;
import org.jmock.States;
import org.jmock.api.Expectation;
import org.jmock.internal.ExpectationBuilder;
import org.unitils.core.Unitils;
import org.unitils.core.UnitilsException;

public class JmockUnitils {
	public static void checking(ExpectationBuilder expectations) {
		context().checking(expectations);
	}

	public static void assertIsSatisfied() {
		context().assertIsSatisfied();
	}

	public static States states(String name) {
		return context().states(name);
	}

	public static void addExpectation(Expectation expectation) {
		context().addExpectation(expectation);
	}

	private static JmockModule getJmockModule() {
		Unitils unitils = Unitils.getInstance();
		JmockModule module = unitils.getModulesRepository().getModuleOfType(JmockModule.class);
		if (module == null) {
			throw new UnitilsException("Unable to find an instance of an JmockModule in the modules repository.");
		}
		return module;
	}

	public static Mockery context() {
		return getJmockModule().getMockery();
	}
}
