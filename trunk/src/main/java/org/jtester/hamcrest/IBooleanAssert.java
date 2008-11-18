package org.jtester.hamcrest;

public interface IBooleanAssert extends IAssert<Boolean, IBooleanAssert> {
	IBooleanAssert is(boolean bl);

	IBooleanAssert is(boolean bl, String description);
	// TODO
}
