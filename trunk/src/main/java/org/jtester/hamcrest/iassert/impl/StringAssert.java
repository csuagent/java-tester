package org.jtester.hamcrest.iassert.impl;

import org.hamcrest.Matcher;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.IsEqualIgnoringWhiteSpace;
import org.hamcrest.text.StringContains;
import org.hamcrest.text.StringEndsWith;
import org.hamcrest.text.StringStartsWith;
import org.jtester.hamcrest.iassert.IStringAssert;

public class StringAssert extends ComparableAssert<String, IStringAssert> implements IStringAssert {
	public StringAssert(Class<IStringAssert> clazE) {
		super(clazE);
	}

	public StringAssert(String value, Class<? extends IStringAssert> clazE) {
		super(value, clazE);
	}

	public StringAssert(Class<String> clazT, Class<? extends IStringAssert> clazE) {
		super(clazT, clazE);
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
		Matcher<String> matcher = IsEqualIgnoringCase.equalToIgnoringCase(string);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert regular(String regular) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStringAssert start(String expected) {
		Matcher<String> matcher = StringStartsWith.startsWith(expected);
		return (IStringAssert) this.assertThat(matcher);
	}

	public Matcher<String> eqIgnorBlank(String string) {
		Matcher<String> matcher = IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace(string);
		return (IStringAssert) this.assertThat(matcher);
	}
}
