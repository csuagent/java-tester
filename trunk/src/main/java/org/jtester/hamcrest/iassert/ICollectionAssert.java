package org.jtester.hamcrest.iassert;

import java.util.Collection;

public interface ICollectionAssert<T> extends
		IAssert<Collection<T>, ICollectionAssert<T>>,
		IReflectionAssert<T, ICollectionAssert<T>> {
	ICollectionAssert<T> hasItems(Collection<T> collection);

	ICollectionAssert<T> hasItems(T value, T... values);

	ICollectionAssert<T> hasItems(T[] values);

	// boolean
	ICollectionAssert<T> hasItems(boolean values[]);

	// byte
	ICollectionAssert<T> hasItems(byte values[]);

	// char
	ICollectionAssert<T> hasItems(char values[]);

	// short int long
	ICollectionAssert<T> hasItems(short values[]);

	ICollectionAssert<T> hasItems(int values[]);

	ICollectionAssert<T> hasItems(long values[]);

	// float double
	ICollectionAssert<T> hasItems(float values[]);

	ICollectionAssert<T> hasItems(double values[]);

	ICollectionAssert<T> sizeIs(int size);

	// TODO
}
