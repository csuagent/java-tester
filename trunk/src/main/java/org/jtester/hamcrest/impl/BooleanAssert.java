package org.jtester.hamcrest.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
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
		return super.isEqualTo(bl);
	}

	public IBooleanAssert is(boolean bl, String message) {
		Matcher matcher = IsEqual.equalTo(bl);
		return this.assertThat(matcher, message);
	}
}
