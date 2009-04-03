package org.jtester.hamcrest.iassert.common;

public interface IObjectContainerAssert<E extends ICommonAssert<?, ?>> {
	E sizeIs(int size);

	E sizeEq(int size);

	E sizeGt(int size);

	E sizeGe(int size);

	E sizeLt(int size);

	E sizeLe(int size);

	<T extends Object> E hasItems(T value, T... values);

	<T extends Object> E hasItems(T[] values);

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

	E hasItemMatcher(String regular, String... regulars);

	E allItemMatcher(String regular);
}
