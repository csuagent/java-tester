package org.jtester.hamcrest;

public interface INumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, E>> extends IAssert<T, E> {
	public E lt(T max);

	public E leq(T max);

	public E gt(T min);

	public E geq(T min);

	public E between(T min, T max);
}
