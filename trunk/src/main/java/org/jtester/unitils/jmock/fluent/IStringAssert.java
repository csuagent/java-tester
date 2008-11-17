package org.jtester.unitils.jmock.fluent;

public interface IStringAssert extends IAssert<String, IStringAssert> {
	public IStringAssert containsString(String expected);

	public IStringAssert endWith(String expected);

	public IStringAssert startsWith(String expected);

//	public IStringAssert regular(String regular);

	public IStringAssert eqIgnoreCase(String item);
}
