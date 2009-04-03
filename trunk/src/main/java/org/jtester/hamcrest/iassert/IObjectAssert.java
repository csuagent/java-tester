package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.commoninterface.ICommonAssert;
import org.jtester.hamcrest.iassert.commoninterface.IReflectionAssert;

public interface IObjectAssert extends ICommonAssert<Object, IObjectAssert>,
		IReflectionAssert<IObjectAssert> {

}
