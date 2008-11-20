package org.jtester.hamcrest.iassert;

public interface IObjectAssert<T, E extends IObjectAssert<T, ?>> extends IAssert<T, E>, IReflectionAssert<T, E> {

}
