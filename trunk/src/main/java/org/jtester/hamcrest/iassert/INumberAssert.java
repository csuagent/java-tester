package org.jtester.hamcrest.iassert;

public interface INumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>> extends
		IComparableAssert<T, E> {

}
