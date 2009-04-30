package org.jtester.hamcrest.iassert.object.impl;

import java.util.Calendar;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.ICalendarAssert;
import org.jtester.hamcrest.matcher.calendar.CalendarEqualsMatcher;
import org.jtester.hamcrest.matcher.calendar.CalendarEqualsMatcher.CalendarFieldType;

public class CalendarAssert extends BaseAssert<Calendar, ICalendarAssert> implements ICalendarAssert {

	public CalendarAssert() {
		super(ICalendarAssert.class);
	}

	public CalendarAssert(Calendar calendar) {
		super(calendar, ICalendarAssert.class);
	}

	public ICalendarAssert yearIs(int year) {
		return this.assertThat(year, CalendarFieldType.YEAR);
	}

	public ICalendarAssert yearIs(String year) {
		return this.assertThat(year, CalendarFieldType.YEAR);
	}

	public ICalendarAssert dayIs(int day) {
		return this.assertThat(day, CalendarFieldType.DATE);
	}

	public ICalendarAssert dayIs(String day) {
		return this.assertThat(day, CalendarFieldType.DATE);
	}

	public ICalendarAssert hourIs(int hour) {
		return this.assertThat(hour, CalendarFieldType.HOUR);
	}

	public ICalendarAssert hourIs(String hour) {
		return this.assertThat(hour, CalendarFieldType.HOUR);
	}

	public ICalendarAssert minuteIs(int minute) {
		return this.assertThat(minute, CalendarFieldType.MINUTE);
	}

	public ICalendarAssert minuteIs(String minute) {
		return this.assertThat(minute, CalendarFieldType.MINUTE);
	}

	public ICalendarAssert monthIs(int month) {
		return this.assertThat(month, CalendarFieldType.MONTH);
	}

	public ICalendarAssert monthIs(String month) {
		return this.assertThat(month, CalendarFieldType.MONTH);
	}

	public ICalendarAssert secondIs(int second) {
		return this.assertThat(second, CalendarFieldType.SECOND);
	}

	public ICalendarAssert secondIs(String second) {
		return this.assertThat(second, CalendarFieldType.SECOND);
	}

	private ICalendarAssert assertThat(int value, CalendarFieldType type) {
		CalendarEqualsMatcher matcher = new CalendarEqualsMatcher(value, type);
		return this.assertThat(matcher);
	}

	private ICalendarAssert assertThat(String value, CalendarFieldType type) {
		CalendarEqualsMatcher matcher = new CalendarEqualsMatcher(Integer.valueOf(value), type);
		return this.assertThat(matcher);
	}
}
