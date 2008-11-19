package org.jtester.hamcrest.impl;

import java.util.Collection;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.ICollectionAssert;

public class CollectionAssert<T extends Collection, E extends ICollectionAssert<T, ?>> extends BaseAssert<T, E>
		implements ICollectionAssert<T, E> {

	public CollectionAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public CollectionAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public CollectionAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public E hasItems(Collection collection) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(T[] value) {
		// TODO Auto-generated method stub
		return null;
	}

}
