package org.jtester.unitils.jmock.fluent.impl;

import org.jtester.unitils.jmock.fluent.IAssert;
import org.jtester.unitils.jmock.fluent.IBooleanAssert;

public class BooleanAssert extends BaseAssert<Boolean, IBooleanAssert> implements IBooleanAssert {

	public BooleanAssert(Boolean value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public BooleanAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public BooleanAssert(Class<Boolean> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

}
