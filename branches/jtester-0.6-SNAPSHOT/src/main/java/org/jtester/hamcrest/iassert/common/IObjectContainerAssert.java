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
	 * the size of a collection or the length of an array should be equal to the
	 * expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeIs(int size);

	/**
	 * the size of a collection or the length of an array should be equal to the
	 * expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeEq(int size);

	/**
	 * the size of a collection or the length of an array should be greater than
	 * the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeGt(int size);

	/**
	 * the size of a collection or the length of an array should be greater than
	 * or equal to the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeGe(int size);

	/**
	 * the size of a collection or the length of an array should be less than
	 * the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeLt(int size);

	/**
	 * the size of a collection or the length of an array should be less then or
	 * equal to the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeLe(int size);

	/**
	 * the size of a collection or the length of an array should be not equal to
	 * the expected size
	 * 
	 * @param size
	 *            the expected size
	 * @return
	 */
	E sizeNe(int size);

	E hasItems(Collection<?> coll);

	E hasItems(Object value, Object... values);

	E hasItems(Object[] values);

	// boolean
	E hasItems(boolean values[]);

	// byte
	E hasItems(byte values[]);

	// char
	E hasItems(char values[]);

	// short int long
	E hasItems(short values[]);

	E hasItems(int values[]);

	E hasItems(long values[]);

	// float double
	E hasItems(float values[]);

	E hasItems(double values[]);

	E hasItemMatch(String regular, String... regulars);

	E allItemMatch(String regular, String... regulars);
}
