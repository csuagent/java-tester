package org.jtester.hamcrest.iassert.common;

@SuppressWarnings("unchecked")
public interface IComparableAssert<E extends IAssert<?, ?>> {

	/**
	 * the asserted object less than $max
	 * 
	 * @param max
	 * @return
	 */
	public E lessThan(Comparable max);

	/**
	 * the asserted object less than or equals to $max
	 * 
	 * @param max
	 * @return
	 */
	public E lessEqual(Comparable max);

	/**
	 * the asserted object greater than $min
	 * 
	 * @param min
	 * @return
	 */
	public E greaterThan(Comparable min);

	/**
	 * the asserted object greater than or equals to $min
	 * 
	 * @param min
	 * @return
	 */
	public E greaterEqual(Comparable min);

	/**
	 * the asserted object is between $min and $max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public E between(Comparable min, Comparable max);
}
