package org.jtester.hamcrest;

import java.util.Collection;

public interface ICollectionAssert<T extends Collection, E extends ICollectionAssert<T, ?>> extends IAssert<T, E> {
	E hasItems(Collection collection);

	E hasItems(T value[]);

	// TODO
}
