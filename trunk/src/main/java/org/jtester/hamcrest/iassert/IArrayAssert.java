package org.jtester.hamcrest.iassert;

import java.util.Collection;

public interface IArrayAssert<T, E extends IArrayAssert<T, ?>> extends
		IAssert<T, E>, IReflectionAssert<T, E> {
	E hasItems(T item, T... items);

	E hasItems(Collection<?> collection);
}
