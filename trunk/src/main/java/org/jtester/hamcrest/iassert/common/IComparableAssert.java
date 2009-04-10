package org.jtester.hamcrest.iassert.common;

@SuppressWarnings("unchecked")
public interface IComparableAssert<E extends IAssert<?, ?>> {

	/**
	 * the object asserted less than $max
	 * 
	 * @param max
	 * @return
	 */
	public E lessThan(Comparable max);

	/**
	 * the object asserted less than or equals to $max
	 * 
	 * @param max
	 * @return
	 */
	public E lessEqual(Comparable max);

	/**
	 * the object asserted greater than $min
	 * 
	 * @param min
	 * @return
	 */
	public E greaterThan(Comparable min);

	/**
	 * the object asserted greater than or equals to $min
	 * 
	 * @param min
	 * @return
	 */
	public E greaterEqual(Comparable min);

	/**
	 * the object asserted is between $min and $max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public E between(Comparable min, Comparable max);
}
