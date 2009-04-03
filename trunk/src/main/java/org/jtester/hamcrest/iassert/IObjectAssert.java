package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.commoninterface.ICommonAssert;

public interface IObjectAssert extends ICommonAssert<Object, IObjectAssert>,
		IReflectionAssert<IObjectAssert> {

}
