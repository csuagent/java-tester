package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IComparableAssert;

public interface INumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>> extends
		IComparableAssert<T, E> {

}
