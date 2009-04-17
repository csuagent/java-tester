package org.jtester.hamcrest.iassert.object;

import org.hamcrest.Matcher;
import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.IComparableAssert;

public interface IStringAssert extends IBaseAssert<String, IStringAssert>, IComparableAssert<IStringAssert> {
	/**
	 * the asserted string should contain the expected sub-string
	 * 
	 * @param expected
	 * @return
	 */
	public IStringAssert contains(String expected);

	/**
	 * the asserted string should end with the expected sub-string
	 * 
	 * @param expected
	 * @return
	 */
	public IStringAssert end(String expected);

	/**
	 * the asserted string should start with the expected sub-string
	 * 
	 * @param expected
	 * @return
	 */
	public IStringAssert start(String expected);

	/**
	 * the asserted string should match the expected regular expression
	 * 
	 * @param regex
	 * @return
	 */
	public IStringAssert regular(String regex);

	/**
	 * the asserted string is equal to the expected string ignoring case
	 * considerations
	 * 
	 * @param string
	 * @return
	 */
	public IStringAssert eqIgnoreCase(String string);

	/**
	 * the asserted string is equal to the expected string ignoring blank space
	 * considerations
	 * 
	 * @param string
	 * @return
	 */
	public Matcher<String> eqIgnorBlank(String string);
}
