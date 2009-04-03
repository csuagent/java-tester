package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.common.ICommonAssert;

public interface IBooleanAssert extends ICommonAssert<Boolean, IBooleanAssert> {
	IBooleanAssert is(boolean bl);

	IBooleanAssert is(boolean bl, String description);
}
