package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

/**
 * 布尔值断言接口
 * 
 * @author darui.wudr
 * 
 */
public interface IBooleanAssert extends IBaseAssert<Boolean, IBooleanAssert> {
	IBooleanAssert is(boolean bl);

	IBooleanAssert is(boolean bl, String description);
}
