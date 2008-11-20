package org.jtester.hamcrest.iassert;

import org.hamcrest.Matcher;

public interface IStringAssert extends IComparableAssert<String, IStringAssert> {
	public IStringAssert contains(String expected);

	public IStringAssert end(String expected);

	public IStringAssert start(String expected);

	public IStringAssert regular(String regular);

	public IStringAssert eqIgnoreCase(String string);

	public Matcher<String> eqIgnorBlank(String string);
}
