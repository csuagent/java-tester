package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IAssert;

public interface IBooleanAssert extends IAssert<Boolean, IBooleanAssert> {
	IBooleanAssert is(boolean bl);

	IBooleanAssert is(boolean bl, String description);
}
