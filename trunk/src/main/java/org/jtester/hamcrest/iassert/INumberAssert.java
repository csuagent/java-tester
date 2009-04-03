package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.commoninterface.IComparableAssert;

public interface INumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>> extends
		IComparableAssert<T, E> {

}
