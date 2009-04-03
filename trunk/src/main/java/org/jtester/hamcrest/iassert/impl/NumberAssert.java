package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.INumberAssert;
import org.jtester.hamcrest.iassert.common.ICommonAssert;

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
