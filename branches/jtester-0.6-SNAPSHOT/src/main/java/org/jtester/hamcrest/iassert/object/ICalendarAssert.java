package org.jtester.hamcrest.iassert.object;

import java.util.Calendar;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

public interface ICalendarAssert extends IBaseAssert<Calendar, ICalendarAssert> {
	public ICalendarAssert isYear(int year);

	public ICalendarAssert isYear(String year);

	// TODO
}
