package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.commoninterface.ICommonAssert;

public interface IBaseAssert<T, E extends ICommonAssert<T, ?>> extends ICommonAssert<T, E> {

}
