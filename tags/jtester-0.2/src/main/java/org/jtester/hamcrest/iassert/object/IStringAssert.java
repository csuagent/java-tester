package org.jtester.hamcrest.iassert.object;

import org.hamcrest.Matcher;
import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.IComparableAssert;

public interface IStringAssert extends IBaseAssert<String, IStringAssert>, IComparableAssert<IStringAssert> {
	public IStringAssert contains(String expected);

	public IStringAssert end(String expected);

	public IStringAssert start(String expected);

	public IStringAssert regular(String regex);

	public IStringAssert eqIgnoreCase(String string);

	public Matcher<String> eqIgnorBlank(String string);
}
