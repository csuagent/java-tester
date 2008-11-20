package org.jtester.hamcrest;

public interface IBaseAssert<T, E extends IAssert<T, ?>> extends IAssert<T, E>, IReflectionAssert<T, E> {

}
