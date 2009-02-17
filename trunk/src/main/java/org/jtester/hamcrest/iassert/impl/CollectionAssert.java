package org.jtester.hamcrest.iassert.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsCollectionContaining;
import org.hamcrest.core.AllOf;
import org.jtester.core.ArrayConvertor;
import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.ICollectionAssert;

public class CollectionAssert<T extends Collection<?>, E extends ICollectionAssert<T, ?>>
		extends BaseAssert<T, E> implements ICollectionAssert<T, E> {

	public CollectionAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public CollectionAssert(Class<T> clazT, Class<? extends IAssert<?, ?>> clazE) {
		super(clazT, clazE);
	}

	public CollectionAssert(T value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public E hasItems(Collection<?> collection) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (collection != null) {
			for (Object item : collection) {
				list.add(IsCollectionContaining.hasItem(item));
			}
		}
		return assertThat(AllOf.allOf(list));
	}

	public E hasItems(Object value, Object... values) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(IsCollectionContaining.hasItem(value));
		for (Object item : values) {
			list.add(IsCollectionContaining.hasItem(item));
		}
		return assertThat(AllOf.allOf(list));
	}

	public <F> E hasItems(F[] values) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (values != null) {
			for (Object item : values) {
				list.add(IsCollectionContaining.hasItem(item));
			}
		}
		return assertThat(AllOf.allOf(list));
	}

	public E hasItems(int[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(boolean[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(byte[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(char[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(short[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(long[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(float[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}

	public E hasItems(double[] values) {
		return this.hasItems(ArrayConvertor.convert(values));
	}
}
