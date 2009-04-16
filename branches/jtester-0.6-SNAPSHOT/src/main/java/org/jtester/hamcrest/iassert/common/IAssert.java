package org.jtester.hamcrest.iassert.common;

import org.hamcrest.Matcher;

public interface IAssert<T, E extends IAssert<T, ?>> extends Matcher<T> {
	/**
	 * demand an end to jmock parameter expected.<br>
	 * 
	 * @return a default value to pass java compiling
	 */
	public T wanted();
}
