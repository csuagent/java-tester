package org.jtester.hamcrest.iassert.object;

import java.util.Calendar;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

public interface ICalendarAssert extends IBaseAssert<Calendar, ICalendarAssert> {
	public ICalendarAssert yearIs(int year);

	public ICalendarAssert yearIs(String year);

	public ICalendarAssert monthIs(int month);

	public ICalendarAssert monthIs(String month);

	public ICalendarAssert dayIs(int day);

	public ICalendarAssert dayIs(String day);

	public ICalendarAssert hourIs(int hour);

	public ICalendarAssert hourIs(String hour);

	public ICalendarAssert minuteIs(int minute);

	public ICalendarAssert minuteIs(String minute);

	public ICalendarAssert secondIs(int second);

	public ICalendarAssert secondIs(String second);
}
