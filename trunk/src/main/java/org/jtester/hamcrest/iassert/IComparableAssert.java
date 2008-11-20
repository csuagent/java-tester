package org.jtester.hamcrest.iassert;

public interface IComparableAssert<T extends Comparable<T>, E extends IComparableAssert<T, ?>> extends IAssert<T, E> {
	public E lt(T max);

	public E leq(T max);

	public E gt(T min);

	public E geq(T min);

	public E between(T min, T max);
}
