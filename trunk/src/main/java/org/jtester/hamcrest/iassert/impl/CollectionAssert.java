package org.jtester.hamcrest.iassert.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsCollectionContaining;
import org.hamcrest.core.AllOf;
import org.jtester.core.ArrayConvertor;
import org.jtester.hamcrest.iassert.ICollectionAssert;

public class CollectionAssert<T> extends
		BaseAssert<Collection<T>, ICollectionAssert<T>> implements
		ICollectionAssert<T> {

	public CollectionAssert(Class<? extends ICollectionAssert<T>> clazE) {
		super(clazE);
	}

	public CollectionAssert(Collection<T> value,
			Class<? extends ICollectionAssert<T>> clazE) {
		super(value, clazE);
	}

	public ICollectionAssert<T> hasItems(Collection<T> collection) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (collection != null) {
			for (Object item : collection) {
				list.add(IsCollectionContaining.hasItem(item));
			}
		}
		return assertThat(AllOf.allOf(list));
	}

	public ICollectionAssert<T> hasItems(T value, T... values) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(IsCollectionContaining.hasItem(value));
		for (Object item : values) {
			list.add(IsCollectionContaining.hasItem(item));
		}
		return assertThat(AllOf.allOf(list));
	}

	public ICollectionAssert<T> hasItems(Object[] values) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (values != null) {
			for (Object item : values) {
				list.add(IsCollectionContaining.hasItem(item));
			}
		}
		return assertThat(AllOf.allOf(list));
	}

	public ICollectionAssert<T> hasItems(int[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(boolean[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(byte[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(char[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(short[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(long[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(float[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> hasItems(double[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public ICollectionAssert<T> sizeIs(int size) {
		// TODO Auto-generated method stub
		return null;
	}
}
