package org.jtester.core;

import org.jtester.hamcrest.iassert.object.intf.IArrayAssert;
import org.jtester.hamcrest.iassert.object.intf.IBooleanAssert;
import org.jtester.hamcrest.iassert.object.intf.IDoubleAssert;
import org.jtester.hamcrest.iassert.object.intf.IFloatAssert;
import org.jtester.hamcrest.iassert.object.intf.IIntegerAssert;
import org.jtester.hamcrest.iassert.object.intf.ILongAssert;
import org.jtester.hamcrest.iassert.object.intf.IShortAssert;
import org.jtester.hamcrest.iassert.object.intf.IStringAssert;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class JTesterAssertTest extends JTester {

	public void wantAssert() {
		want.object(want.string(new String())).type(IStringAssert.class);
		want.object(want.bool(true)).type(IBooleanAssert.class);
		want.object(want.bool(Boolean.TRUE)).type(IBooleanAssert.class);
		// // number
		want.object(want.number(Short.valueOf("1"))).type(IShortAssert.class);
		want.object(want.number(1)).type(IIntegerAssert.class);
		want.object(want.number(1L)).type(ILongAssert.class);
		want.object(want.number(1f)).type(IFloatAssert.class);
		want.object(want.number(1d)).type(IDoubleAssert.class);

		want.object(want.array(new boolean[] {})).type(IArrayAssert.class);
	}
}
