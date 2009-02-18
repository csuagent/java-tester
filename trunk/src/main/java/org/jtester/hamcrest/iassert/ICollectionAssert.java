package org.jtester.hamcrest.iassert;

import java.util.Collection;

public interface ICollectionAssert extends
		IAssert<Collection<?>, ICollectionAssert>,
		IReflectionAssert<Collection<?>, ICollectionAssert> {
	ICollectionAssert hasItems(Collection<?> collection);

	<T extends Object> ICollectionAssert hasItems(T value, T... values);

	<T extends Object> ICollectionAssert hasItems(T[] values);

	// boolean
	ICollectionAssert hasItems(boolean values[]);

	// byte
	ICollectionAssert hasItems(byte values[]);

	// char
	ICollectionAssert hasItems(char values[]);

	// short int long
	ICollectionAssert hasItems(short values[]);

	ICollectionAssert hasItems(int values[]);

	ICollectionAssert hasItems(long values[]);

	// float double
	ICollectionAssert hasItems(float values[]);

	ICollectionAssert hasItems(double values[]);

	ICollectionAssert sizeIs(int size);

	// TODO
}
