package org.jtester.hamcrest.iassert.object.impl;

import java.util.Collection;

import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.ICollectionAssert;

public class CollectionAssert extends AllAssert<Collection<?>, ICollectionAssert> implements ICollectionAssert {

	public CollectionAssert() {
		super(ICollectionAssert.class);
		this.valueClaz = Collection.class;
	}

	public CollectionAssert(Collection<?> value) {
		super(value, ICollectionAssert.class);
		this.valueClaz = Collection.class;
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
