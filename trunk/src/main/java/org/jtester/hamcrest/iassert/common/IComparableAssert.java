package org.jtester.hamcrest.iassert.common;

@SuppressWarnings("unchecked")
public interface IComparableAssert<E extends IAssert<?, ?>> {

	public E lt(Comparable max);

	public E leq(Comparable max);

	public E gt(Comparable min);

	public E geq(Comparable min);

	public E between(Comparable min, Comparable max);
}
