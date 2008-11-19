package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IArrayAssert;
import org.jtester.hamcrest.IAssert;

public class ArrayAssert<T, E extends IArrayAssert<T, E>> extends BaseAssert<T, E> implements IArrayAssert<T, E> {

	public ArrayAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ArrayAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public ArrayAssert(T[] value, Class<? extends IAssert> clazE) {
		super(clazE);
		this.value = value;
		this.type = AssertType.AssertThat;
	}
}
