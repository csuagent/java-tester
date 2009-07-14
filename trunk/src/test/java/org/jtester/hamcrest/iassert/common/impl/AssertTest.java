package org.jtester.hamcrest.iassert.common.impl;

import java.io.File;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.jtester.hamcrest.iassert.common.intf.IAssert;
import org.jtester.testng.JTester;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class AssertTest extends JTester {
	@Test(dataProvider = "assertClass")
	public void wanted(IAssert<?, ?> as, Class<?> claz) {
		want.object(as).propertyEq("valueClaz", claz);
	}

	@DataProvider
	public Object[][] assertClass() {
		return new Object[][] { { the.bool(), Boolean.class }, { the.array(), Object[].class },
				{ the.bite(), Byte.class }, { the.calendar(), Calendar.class }, { the.character(), Character.class },
				{ the.collection(), Collection.class }, { the.date(), Date.class }, { the.doublenum(), Double.class },
				{ the.file(), File.class }, { the.floatnum(), Float.class }, { the.integer(), Integer.class },
				{ the.longnum(), Long.class }, { the.map(), Map.class }, { the.object(), Object.class },
				{ the.shortnum(), Short.class }, { the.string(), String.class } };
	}

	@Test
	public void wantedMap() {
		want.object(the.map()).propertyEq("valueClaz", Map.class);
	}
}
