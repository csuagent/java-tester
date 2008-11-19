package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.INumberAssert;

public class NumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>> extends
		ComparableAssert<T, E> implements INumberAssert<T, E> {

	public NumberAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public NumberAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public NumberAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}
}
