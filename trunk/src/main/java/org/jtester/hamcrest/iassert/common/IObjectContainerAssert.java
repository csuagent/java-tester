package org.jtester.hamcrest.iassert.common;

import java.util.Collection;

public interface IObjectContainerAssert<E extends IAssert<?, ?>> {
	E sizeIs(int size);

	E sizeEq(int size);

	E sizeGt(int size);

	E sizeGe(int size);

	E sizeLt(int size);

	E sizeLe(int size);

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

	E hasItemMatcher(String regular, String... regulars);

	E allItemMatcher(String regular);
}
