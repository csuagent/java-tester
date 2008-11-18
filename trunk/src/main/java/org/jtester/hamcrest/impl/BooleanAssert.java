package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.IBooleanAssert;

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

	public IBooleanAssert is(boolean bl) {
		return super.eq(bl);
	}
}
