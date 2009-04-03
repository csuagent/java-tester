package org.jtester.hamcrest.iassert.common;

import org.hamcrest.Matcher;

public interface IAssert<T, E extends IAssert<T, ?>> extends Matcher<T> {

}
