package org.jtester.hamcrest.iassert.common;

import java.util.Collection;

/**
 * 
 * @author darui.wudr
 * 
 * @param <E>
 */
public interface IObjectContainerAssert<E extends IAssert<?, ?>> {
	/**
	 * the size of a collection or the length of an array should equal to the
	 * expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeIs(int size);

	/**
	 * the size of a collection or the length of an array should equal to the
	 * expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeEq(int size);

	/**
	 * the size of a collection or the length of an array should greater than
	 * the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeGt(int size);

	/**
	 * the size of a collection or the length of an array should greater than or
	 * equal to the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeGe(int size);

	/**
	 * the size of a collection or the length of an array should less than the
	 * expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeLt(int size);

	/**
	 * the size of a collection or the length of an array should less then or
	 * equal to the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeLe(int size);

	/**
	 * the size of a collection or the length of an array should not equal to
	 * the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeNe(int size);

	/**
	 * the array or collection should contain the expected items in the para's
	 * collection
	 * 
	 * @param coll
	 *            a collection contains expected items
	 * @return
	 */
	E hasItems(Collection<?> coll);

	/**
	 * the array or collection should contain the expected objects.
	 * 
	 * @param value
	 * @param values
	 * @return
	 */
	E hasItems(Object value, Object... values);

	/**
	 * the array or collection should contain the expected objects.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(Object[] values);

	/**
	 * the array or collection should contain the expected boolean values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(boolean values[]);

	/**
	 * the array or collection should contain the expected byte values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(byte values[]);

	/**
	 * the array or collection should contain the expected character values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(char values[]);

	/**
	 * the array or collection should contain the expected short number values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(short values[]);

	/**
	 * the array or collection should contain the expected integer number
	 * values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(int values[]);

	/**
	 * the array or collection should contain the expected long number values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(long values[]);

	/**
	 * the array or collection should contain the expected float number values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(float values[]);

	/**
	 * the array or collection should contain the expected double number values.
	 * 
	 * @param values
	 * @return
	 */
	E hasItems(double values[]);

	/**
	 * at least one of items contained in the array or the collection must match
	 * the those regular expression.
	 * 
	 * @param values
	 * @return
	 */
	E hasItemMatch(String regular, String... regulars);

	/**
	 * all of items contained in the array or the collection must match the
	 * those regular expression.
	 * 
	 * @param regular
	 * @param regulars
	 * @return
	 */
	E allItemMatch(String regular, String... regulars);
}
