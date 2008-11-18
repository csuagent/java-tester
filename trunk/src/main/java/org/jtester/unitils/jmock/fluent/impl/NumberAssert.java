package org.jtester.unitils.jmock.fluent.impl;

import org.jtester.unitils.jmock.fluent.IAssert;
import org.jtester.unitils.jmock.fluent.INumberAssert;
import org.mockito.internal.matchers.LessThan;

public class NumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, E>> extends BaseAssert<T, E>
		implements INumberAssert<T, E> {

	public NumberAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public NumberAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public NumberAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public E geq(int min) {
		// TODO Auto-generated method stub
		return null;
	}

	public E gt(int min) {
		// TODO Auto-generated method stub
		return null;
	}

	public E leq(int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public E lt(int max) {
		LessThan<Integer> matcher = new LessThan<Integer>(max);
		return this.link(matcher);
	}
}
