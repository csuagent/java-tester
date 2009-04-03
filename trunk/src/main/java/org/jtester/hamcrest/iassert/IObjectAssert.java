package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.common.ICommonAssert;
import org.jtester.hamcrest.iassert.common.IReflectionAssert;

public interface IObjectAssert extends ICommonAssert<Object, IObjectAssert>,
		IReflectionAssert<IObjectAssert> {

}
