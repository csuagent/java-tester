package org.jtester.hamcrest.iassert.common.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.common.IAssert;
import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.IObjectContainerAssert;
import org.jtester.hamcrest.matcher.SizeOrLengthMatcher;

public class ObjectContainerAssert<T, E extends IBaseAssert<T, ?>> extends ComparableAssert<T, E> implements
		IObjectContainerAssert<T, E> {

	public ObjectContainerAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ObjectContainerAssert(T value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public E allItemMatcher(String regular) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItemMatcher(String regular, String... regulars) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(T[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(boolean[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(byte[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(char[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(short[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(int[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(long[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(float[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(double[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public E sizeEq(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public E sizeGe(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public E sizeGt(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public E sizeLe(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public E sizeLt(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public E hasItems(T item, T... items) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		Matcher<?> matcher1 = IsArrayContaining.hasItemInArray(item);
		list.add(matcher1);
		if (items != null) {
			for (Object temp : items) {
				Matcher<?> matcher2 = IsArrayContaining.hasItemInArray(temp);
				list.add(matcher2);
			}
		}
		Matcher<?> matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}

	public E hasItems(Collection<?> collection) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		if (collection != null) {
			for (Object temp : collection) {
				Matcher<?> _matcher = IsArrayContaining.hasItemInArray(temp);
				list.add(_matcher);
			}
		}
		Matcher<?> matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}

	public E sizeIs(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size);
		return this.assertThat(matcher);
	}
}
