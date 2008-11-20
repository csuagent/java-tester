package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.IObjectAssert;

public class ObjectAssert<T, E extends IObjectAssert<T, ?>> extends BaseAssert<T, E> implements IObjectAssert<T, E> {

	public ObjectAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ObjectAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public ObjectAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}
}
