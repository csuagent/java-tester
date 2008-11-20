package org.jtester.hamcrest.iassert.impl;

import java.util.Map;

import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.IMapAssert;

public class MapAssert<T extends Map, E extends IMapAssert<T, ?>> extends BaseAssert<T, E> implements IMapAssert<T, E> {

	public MapAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public MapAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public MapAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

}
