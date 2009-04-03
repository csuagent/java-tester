package org.jtester.hamcrest.iassert.object.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.IBooleanAssert;

public class BooleanAssert extends AllAssert<Boolean, IBooleanAssert> implements IBooleanAssert {

	public BooleanAssert() {
		super(IBooleanAssert.class);
	}

	public BooleanAssert(Boolean value) {
		super(value, IBooleanAssert.class);
	}

	public IBooleanAssert is(boolean bl) {
		return super.isEqualTo(bl);
	}

	public IBooleanAssert is(boolean bl, String message) {
		Matcher<?> matcher = IsEqual.equalTo(bl);
		return this.assertThat(matcher, message);
	}
}
