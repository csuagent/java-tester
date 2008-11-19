package org.jtester.hamcrest;

import org.hamcrest.Matcher;

public interface IStringAssert extends IAssert<String, IStringAssert> {
	public IStringAssert contains(String expected);

	public IStringAssert end(String expected);

	public IStringAssert start(String expected);

	public IStringAssert regular(String regular);

	public IStringAssert eqIgnoreCase(String string);

	public Matcher<String> eqIgnorBlank(String string);
}
