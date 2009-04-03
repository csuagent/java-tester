package org.jtester.hamcrest.iassert;

import java.util.Collection;

import org.jtester.hamcrest.iassert.commoninterface.ICommonAssert;
import org.jtester.hamcrest.iassert.commoninterface.IReflectionAssert;

public interface IArrayAssert extends ICommonAssert<Object[], IArrayAssert>,
		IReflectionAssert<IArrayAssert> {
	<T extends Object> IArrayAssert hasItems(T item, T... items);

	<T extends Object> IArrayAssert hasItems(Collection<T> collection);
	
	<T extends Object> IArrayAssert sizeIs(int size);
}
