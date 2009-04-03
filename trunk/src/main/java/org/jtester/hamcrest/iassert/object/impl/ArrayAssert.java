package org.jtester.hamcrest.iassert.object.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.IArrayAssert;
import org.jtester.hamcrest.matcher.SizeOrLengthMatcher;
import org.unitils.reflectionassert.ReflectionComparatorMode;

public class ArrayAssert extends AllAssert<Object[], IArrayAssert> implements IArrayAssert {
	public ArrayAssert() {
		super(IArrayAssert.class);
	}

	public <T extends Object> ArrayAssert(T value[]) {
		super(IArrayAssert.class);
		this.value = value;
		this.type = AssertType.AssertThat;
	}

	public <T extends Object> IArrayAssert hasItems(T item, T... items) {
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

	public <T extends Object> IArrayAssert hasItems(Collection<T> collection) {
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

	public <T> IArrayAssert sizeIs(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size);
		return this.assertThat(matcher);
	}

	public IArrayAssert lenientEqualTo(List<Object> expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert lenientEqualTo(Collection<Object> expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert lenientEqualTo(Object[] expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert lenientEqualTo(Object expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert propertyEqualTo(String property, List<Object> expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert propertyEqualTo(String property, Collection<Object> expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert propertyEqualTo(String property, Object[] expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert propertyEqualTo(String property, Object expected) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert reflectionEqualTo(List<Object> expected, ReflectionComparatorMode... modes) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert reflectionEqualTo(Collection<Object> expected, ReflectionComparatorMode... modes) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert reflectionEqualTo(Object[] expected, ReflectionComparatorMode... modes) {
		// TODO Auto-generated method stub
		return null;
	}

	public IArrayAssert reflectionEqualTo(Object expected, ReflectionComparatorMode... modes) {
		// TODO Auto-generated method stub
		return null;
	}
}
