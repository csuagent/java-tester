package org.jtester.hamcrest.iassert;

public interface IBaseAssert<T, E extends IAssert<T, ?>> extends IAssert<T, E>, IReflectionAssert<T, E> {

}
