package org.jtester.core;

import org.jtester.hamcrest.iassert.object.intf.IArrayAssert;
import org.jtester.hamcrest.iassert.object.intf.IBooleanAssert;
import org.jtester.hamcrest.iassert.object.intf.IByteAssert;
import org.jtester.hamcrest.iassert.object.intf.ICalendarAssert;
import org.jtester.hamcrest.iassert.object.intf.ICharacterAssert;
import org.jtester.hamcrest.iassert.object.intf.ICollectionAssert;
import org.jtester.hamcrest.iassert.object.intf.IDoubleAssert;
import org.jtester.hamcrest.iassert.object.intf.IFileAssert;
import org.jtester.hamcrest.iassert.object.intf.IFloatAssert;
import org.jtester.hamcrest.iassert.object.intf.IIntegerAssert;
import org.jtester.hamcrest.iassert.object.intf.ILongAssert;
import org.jtester.hamcrest.iassert.object.intf.IMapAssert;
import org.jtester.hamcrest.iassert.object.intf.INumberAssert;
import org.jtester.hamcrest.iassert.object.intf.IObjectAssert;
import org.jtester.hamcrest.iassert.object.intf.IShortAssert;
import org.jtester.hamcrest.iassert.object.intf.IStringAssert;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class IExpectationTest extends JTester {

	public void theAssert() {
		want.object(the.string()).type(IStringAssert.class);
		want.object(the.bool()).type(IBooleanAssert.class);
		want.object(the.number()).type(INumberAssert.class);
		want.object(the.integer()).type(IIntegerAssert.class);
		want.object(the.longnum()).type(ILongAssert.class);
		want.object(the.doublenum()).type(IDoubleAssert.class);
		want.object(the.floatnum()).type(IFloatAssert.class);
		want.object(the.shortnum()).type(IShortAssert.class);
		want.object(the.character()).type(ICharacterAssert.class);
		want.object(the.bite()).type(IByteAssert.class);
		want.object(the.array()).type(IArrayAssert.class);
		want.object(the.map()).type(IMapAssert.class);
		want.object(the.collection()).type(ICollectionAssert.class);
		want.object(the.object()).type(IObjectAssert.class);
		want.object(the.file()).type(IFileAssert.class);
		want.object(the.calendar()).type(ICalendarAssert.class);
		want.object(the.date()).type(ICalendarAssert.class);
	}
}
