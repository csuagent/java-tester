package org.jtester.hamcrest.iassert.object.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsCollectionContaining;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.ICollectionAssert;

public class CollectionAssert extends AllAssert<Collection<?>, ICollectionAssert> implements ICollectionAssert {

	public CollectionAssert() {
		super(ICollectionAssert.class);
	}

	public CollectionAssert(Collection<?> value) {
		super(value, ICollectionAssert.class);
	}

	public ICollectionAssert hasItems(Collection<?> collection) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (collection != null) {
			for (Object item : collection) {
				list.add(IsCollectionContaining.hasItem(item));
			}
		}
		return assertThat(AllOf.allOf(list));
	}

	public <T extends Object> ICollectionAssert hasItems(T value, T... values) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(IsCollectionContaining.hasItem(value));
		for (Object item : values) {
			list.add(IsCollectionContaining.hasItem(item));
		}
		return assertThat(AllOf.allOf(list));
	}

	public <T extends Object> ICollectionAssert hasItems(T[] values) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (values != null) {
			for (Object item : values) {
				list.add(IsCollectionContaining.hasItem(item));
			}
		}
		return assertThat(AllOf.allOf(list));
	}

	// public ICollectionAssert hasItems(int[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(boolean[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(byte[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(char[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(short[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(long[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(float[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert hasItems(double[] values) {
	// return this.hasItems(ArrayConvertor.convert(values));
	// }
	//
	// public ICollectionAssert sizeIs(int size) {
	// SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size);
	// return this.assertThat(matcher);
	// }
}
