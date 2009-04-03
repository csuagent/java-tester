package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.INumberAssert;

public class NumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>> extends AllAssert<T, E>
		implements INumberAssert<T, E> {

	public NumberAssert(Class<? extends IBaseAssert<?, ?>> clazE) {
		super(clazE);
	}

	// public NumberAssert(Class<T> clazT, Class<? extends IAssert<?, ?>> clazE)
	// {
	// super(clazT, clazE);
	// }

	public NumberAssert(T value, Class<? extends IBaseAssert<?, ?>> clazE) {
		super(value, clazE);
	}
}
