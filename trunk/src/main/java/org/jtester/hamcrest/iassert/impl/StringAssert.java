package org.jtester.hamcrest.iassert.impl;

import org.hamcrest.Matcher;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.IsEqualIgnoringWhiteSpace;
import org.hamcrest.text.StringContains;
import org.hamcrest.text.StringEndsWith;
import org.hamcrest.text.StringStartsWith;
import org.jtester.hamcrest.iassert.object.IStringAssert;
import org.mockito.internal.matchers.Matches;

public class StringAssert extends ComparableAssert<String, IStringAssert>
		implements IStringAssert {
	public StringAssert() {
		super(IStringAssert.class);
	}

	public StringAssert(String str) {
		super(str, IStringAssert.class);
	}

	public IStringAssert contains(String expected) {
		StringContains matcher = new StringContains(expected);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert end(String expected) {
		Matcher<String> matcher = StringEndsWith.endsWith(expected);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert eqIgnoreCase(String string) {
		Matcher<String> matcher = IsEqualIgnoringCase
				.equalToIgnoringCase(string);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert regular(String regex) {
		Matcher<?> matcher = new Matches(regex);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert start(String expected) {
		Matcher<String> matcher = StringStartsWith.startsWith(expected);
		return (IStringAssert) this.assertThat(matcher);
	}

	public Matcher<String> eqIgnorBlank(String string) {
		Matcher<String> matcher = IsEqualIgnoringWhiteSpace
				.equalToIgnoringWhiteSpace(string);
		return (IStringAssert) this.assertThat(matcher);
	}
}
