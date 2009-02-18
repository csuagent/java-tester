package org.jtester.hamcrest.iassert;

import java.util.Collection;

public interface IArrayAssert extends IAssert<Object[], IArrayAssert>,
		IReflectionAssert<IArrayAssert> {
	<T extends Object> IArrayAssert hasItems(T item, T... items);

	<T extends Object> IArrayAssert hasItems(Collection<T> collection);
}
