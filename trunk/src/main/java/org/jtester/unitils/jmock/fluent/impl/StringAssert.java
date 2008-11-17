package org.jtester.unitils.jmock.fluent.impl;

import org.hamcrest.Matcher;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.StringContains;
import org.hamcrest.text.StringEndsWith;
import org.hamcrest.text.StringStartsWith;
import org.jtester.unitils.jmock.fluent.IStringAssert;

public class StringAssert extends BaseAssert<String, IStringAssert> implements IStringAssert {

	public StringAssert(Class<String> clazT, Class<? extends IStringAssert> clazE) {
		super(clazT, clazE);
	}

	@Override
	protected StringAssert getInstance() {
		return new StringAssert(String.class, StringAssert.class);
	}

	public IStringAssert containsString(String expected) {
		StringContains matcher = new StringContains(expected);
		return this.link(matcher);
	}

	public IStringAssert endWith(String expected) {
		Matcher<String> matcher = StringEndsWith.endsWith(expected);
		return this.link(matcher);
	}

	public IStringAssert startsWith(String expected) {
		Matcher<String> matcher = StringStartsWith.startsWith(expected);
		return this.link(matcher);
	}

//	public IStringAssert regular(String regular) {
//		Matcher<String> matcher = StringRegularMatcher.regular(regular);
//		return this.link(matcher);
//	}

	public IStringAssert eqIgnoreCase(String item) {
		Matcher<String> matcher = IsEqualIgnoringCase.equalToIgnoringCase(item);
		return this.link(matcher);
	}
}
