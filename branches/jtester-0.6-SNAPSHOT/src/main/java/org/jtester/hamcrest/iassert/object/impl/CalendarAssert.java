package org.jtester.hamcrest.iassert.object.impl;

import java.util.Calendar;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.ICalendarAssert;

public class CalendarAssert extends BaseAssert<Calendar, ICalendarAssert> implements ICalendarAssert {

	public CalendarAssert() {
		super(ICalendarAssert.class);
	}

	public CalendarAssert(Calendar calendar) {
		super(calendar, ICalendarAssert.class);
	}

	public ICalendarAssert isYear(int year) {
		// this.value.get(Calendar.YEAR) == year
		return null;
	}

	public ICalendarAssert isYear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

}
