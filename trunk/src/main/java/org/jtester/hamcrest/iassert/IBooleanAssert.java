package org.jtester.hamcrest.iassert;

public interface IBooleanAssert extends IAssert<Boolean, IBooleanAssert> {
	IBooleanAssert is(boolean bl);

	IBooleanAssert is(boolean bl, String description);
}
