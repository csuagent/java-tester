package org.jtester.hamcrest.iassert.common.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.collection.IsCollectionContaining;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.common.IAssert;
import org.jtester.hamcrest.iassert.common.IObjectContainerAssert;
import org.jtester.hamcrest.matcher.array.ArrayItemRegularMatcher;
import org.jtester.hamcrest.matcher.array.SizeOrLengthMatcher;
import org.jtester.hamcrest.matcher.array.ArrayItemRegularMatcher.ArrayItemRegularMatcherType;
import org.jtester.hamcrest.matcher.array.SizeOrLengthMatcher.SizeOrLengthMatcherType;
import org.jtester.utility.ArrayConvertor;

public class ObjectContainerAssert<T, E extends IAssert<T, ?>> extends ComparableAssert<T, E> implements
		IObjectContainerAssert<E> {

	public ObjectContainerAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ObjectContainerAssert(T value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public E hasItems(Object item, Object... items) {
		Matcher<?> matcher1 = this.getHasItemMatcher(item);
		if (items == null || items.length == 0) {
			return this.assertThat(matcher1);
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(matcher1);
		for (Object temp : items) {
			Matcher<?> matcher2 = this.getHasItemMatcher(temp);
			list.add(matcher2);
		}
		Matcher<?> matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}

	@SuppressWarnings("unchecked")
	public E hasItems(Object[] values) {
		if (values == null || values.length == 0) {
			return (E) this;
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		for (Object item : values) {
			list.add(this.getHasItemMatcher(item));
		}

		return assertThat(AllOf.allOf(list));
	}

	@SuppressWarnings("unchecked")
	public E hasItems(Collection<?> collection) {
		if (collection == null || collection.size() == 0) {
			return (E) this;
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		for (Object item : collection) {
			list.add(this.getHasItemMatcher(item));
		}

		return assertThat(AllOf.allOf(list));
	}

	private Matcher<?> getHasItemMatcher(Object item) {
		assert valueClaz != null : "the value asserted must not be null";
		if (this.valueClaz == Object[].class) {
			return IsArrayContaining.hasItemInArray(item);
		} else {
			return IsCollectionContaining.hasItem(item);
		}
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

	public E hasItems(int[] values) {
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

	public E sizeIs(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.EQ);
		return this.assertThat(matcher);
	}

	public E sizeEq(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.EQ);
		return this.assertThat(matcher);
	}

	public E sizeGe(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.GE);
		return this.assertThat(matcher);
	}

	public E sizeGt(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.GT);
		return this.assertThat(matcher);
	}

	public E sizeLe(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.LE);
		return this.assertThat(matcher);
	}

	public E sizeLt(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.LT);
		return this.assertThat(matcher);
	}

	public E sizeNe(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.NE);
		return this.assertThat(matcher);
	}

	public E allItemMatch(String regex, String... regexs) {
		ArrayItemRegularMatcher matcher1 = new ArrayItemRegularMatcher(regex, ArrayItemRegularMatcherType.AND);
		if (regexs == null || regexs.length == 0) {
			return this.assertThat(matcher1);
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(matcher1);
		for (String temp : regexs) {
			ArrayItemRegularMatcher matcher2 = new ArrayItemRegularMatcher(temp, ArrayItemRegularMatcherType.AND);
			list.add(matcher2);
		}
		Matcher<?> matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}

	public E hasItemMatch(String regex, String... regexs) {
		ArrayItemRegularMatcher matcher1 = new ArrayItemRegularMatcher(regex, ArrayItemRegularMatcherType.OR);
		if (regexs == null || regexs.length == 0) {
			return this.assertThat(matcher1);
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(matcher1);
		for (String temp : regexs) {
			ArrayItemRegularMatcher matcher2 = new ArrayItemRegularMatcher(temp, ArrayItemRegularMatcherType.OR);
			list.add(matcher2);
		}
		Matcher<?> matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}
}
