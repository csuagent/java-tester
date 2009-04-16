package org.jtester.hamcrest.iassert.object;

import java.util.Collection;

import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.IObjectContainerAssert;
import org.jtester.hamcrest.iassert.common.IReflectionAssert;

public interface ICollectionAssert extends IBaseAssert<Collection<?>, ICollectionAssert>,
		IReflectionAssert<ICollectionAssert>, IObjectContainerAssert<ICollectionAssert> {
}
