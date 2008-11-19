package org.jtester.hamcrest;

import java.util.Collection;

public interface ICollectionAssert<T extends Collection, E extends ICollectionAssert<T, ?>> extends IAssert<T, E> {
	E hasItems(Collection collection);

	E hasItems(Object value, Object... values);

	<F> E hasItems(F values[]);
	
	E hasItems(int values[]);

	// TODO
}
