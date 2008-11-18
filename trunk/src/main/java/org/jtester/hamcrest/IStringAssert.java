package org.jtester.hamcrest;

public interface IStringAssert extends IAssert<String, IStringAssert> {
	public IStringAssert contains(String expected);

	public IStringAssert end(String expected);

	public IStringAssert start(String expected);

	public IStringAssert regular(String regular);

	public IStringAssert eqIgnoreCase(String item);
}
