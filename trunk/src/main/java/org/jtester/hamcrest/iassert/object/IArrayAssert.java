package org.jtester.hamcrest.iassert.object;

import java.util.Collection;

import org.jtester.hamcrest.iassert.common.ICommonAssert;
import org.jtester.hamcrest.iassert.common.IReflectionAssert;

public interface IArrayAssert extends ICommonAssert<Object[], IArrayAssert>,
		IReflectionAssert<IArrayAssert> {
	<T extends Object> IArrayAssert hasItems(T item, T... items);

	<T extends Object> IArrayAssert hasItems(Collection<T> collection);
	
	<T extends Object> IArrayAssert sizeIs(int size);
}
