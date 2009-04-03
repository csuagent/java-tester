package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.common.ICommonAssert;
import org.jtester.hamcrest.iassert.common.impl.ComparableAssert;
import org.jtester.hamcrest.iassert.object.INumberAssert;

public class NumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>>
		extends ComparableAssert<T, E> implements INumberAssert<T, E> {

	public NumberAssert(Class<? extends ICommonAssert<?, ?>> clazE) {
		super(clazE);
	}

	public NumberAssert(Class<T> clazT, Class<? extends ICommonAssert<?, ?>> clazE) {
		super(clazT, clazE);
	}

	public NumberAssert(T value, Class<? extends ICommonAssert<?, ?>> clazE) {
		super(value, clazE);
	}
}
