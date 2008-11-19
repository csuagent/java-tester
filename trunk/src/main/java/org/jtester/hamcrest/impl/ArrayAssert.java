package org.jtester.hamcrest.impl;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.IArrayAssert;
import org.jtester.hamcrest.IAssert;

public class ArrayAssert<T, E extends IArrayAssert<T, E>> extends BaseAssert<T, E> implements IArrayAssert<T, E> {

	public ArrayAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ArrayAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public ArrayAssert(T value[], Class<? extends IAssert> clazE) {
		super(clazE);
		this.value = value;
		this.type = AssertType.AssertThat;
	}

	public E hasItems(Object item, Object... items) {
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		Matcher matcher1 = IsArrayContaining.hasItemInArray(item);
		list.add(matcher1);
		if (items != null) {
			for (Object temp : items) {
				Matcher matcher2 = IsArrayContaining.hasItemInArray(temp);
				list.add(matcher2);
			}
		}
		Matcher<?> matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}
}
