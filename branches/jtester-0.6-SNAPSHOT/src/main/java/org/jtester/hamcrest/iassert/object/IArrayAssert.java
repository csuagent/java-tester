package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.IObjectContainerAssert;
import org.jtester.hamcrest.iassert.common.IReflectionAssert;

/**
 *数组对象断言接口
 * 
 * @author darui.wudr
 * 
 */
public interface IArrayAssert extends IBaseAssert<Object[], IArrayAssert>, IObjectContainerAssert<IArrayAssert>,
		IReflectionAssert<IArrayAssert> {
}
