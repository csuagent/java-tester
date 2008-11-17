package org.jtester.unitils.jmock.fluent.impl;

import org.hamcrest.Matcher;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.StringContains;
import org.hamcrest.text.StringEndsWith;
import org.hamcrest.text.StringStartsWith;
import org.jtester.unitils.jmock.fluent.IStringAssert;

public class StringAssert extends Assert<String, StringAssert> implements IStringAssert {

	public StringExpected(Class<String> clazT, Class<StringExpectedOp> clazE) {
		super(clazT, clazE);
	}

	@Override
	protected StringExpected getInstance() {
		return new StringExpected(String.class, StringExpectedOp.class);
	}

	public StringExpectedOp containsString(String expected) {
		StringContains matcher = new StringContains(expected);
		return this.link(matcher);
	}

	public StringExpectedOp endWith(String expected) {
		Matcher<String> matcher = StringEndsWith.endsWith(expected);
		return this.link(matcher);
	}

	public StringExpectedOp startsWith(String expected) {
		Matcher<String> matcher = StringStartsWith.startsWith(expected);
		return this.link(matcher);
	}

	public StringExpectedOp regular(String regular) {
		Matcher<String> matcher = StringRegularMatcher.regular(regular);
		return this.link(matcher);
	}

	public StringExpectedOp eqIgnoreCase(String item) {
		Matcher<String> matcher = IsEqualIgnoringCase.equalToIgnoringCase(item);
		return this.link(matcher);
	}
}
