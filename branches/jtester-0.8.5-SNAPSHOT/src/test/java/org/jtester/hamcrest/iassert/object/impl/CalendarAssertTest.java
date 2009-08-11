package org.jtester.hamcrest.iassert.object.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jmock.api.ExpectationError;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class CalendarAssertTest extends JTester {
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Date date = null;
	private static Calendar cal = null;
	static {
		try {
			date = format.parse("2009-04-12 15:36:24");
			cal = Calendar.getInstance();
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// @TestedObject
	private TestAppClaz testApp = new TestAppClaz();

	@Mock(injectInto = "testApp")
	// @InjectIntoByType
	private IDateTest idate;

	public void yearIs() {
		want.calendar(cal).yearIs(2009).yearIs("2009");
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().yearIs(2009).yearIs("2009").wanted());
				will.call.one(idate).setCalendar(the.calendar().yearIs(2009).yearIs("2009").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void yearIs_failure1() {
		want.calendar(cal).yearIs(2009).yearIs("20091");
	}

	@Test(expectedExceptions = { ExpectationError.class })
	public void yearIs_failure2() {
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().yearIs(2009).yearIs("2008").wanted());
				will.call.one(idate).setCalendar(the.calendar().yearIs(2009).yearIs("2009").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	@Test(expectedExceptions = { ExpectationError.class })
	public void yearIs_failure3() {
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().yearIs(2009).yearIs("2009").wanted());
				will.call.one(idate).setCalendar(the.calendar().yearIs(2008).wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	public void monthIs() {
		want.calendar(cal).monthIs(4).monthIs("04");
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().monthIs(4).monthIs("04").wanted());
				will.call.one(idate).setCalendar(the.calendar().monthIs(4).monthIs("4").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	public void dayIs() {
		want.calendar(cal).dayIs(12).dayIs("12");
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().dayIs(12).dayIs("12").wanted());
				will.call.one(idate).setCalendar(the.calendar().dayIs(12).dayIs("12").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	public void hourIs() {
		want.calendar(cal).hourIs(15).hourIs("15");
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().hourIs(15).hourIs("15").wanted());
				will.call.one(idate).setCalendar(the.calendar().hourIs(15).hourIs("15").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	public void minuteIs() {
		want.calendar(cal).minuteIs(36).minuteIs("36");
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().minuteIs(36).minuteIs("36").wanted());
				will.call.one(idate).setCalendar(the.calendar().minuteIs(36).minuteIs("36").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	public void secondIs() {
		want.calendar(cal).secondIs(24).secondIs("24");
		checking(new Je() {
			{
				will.call.one(idate).setDate(the.date().secondIs(24).secondIs("24").wanted());
				will.call.one(idate).setCalendar(the.calendar().secondIs(24).secondIs("24").wanted());
			}
		});
		testApp.setTime(date, cal);
	}

	private static interface IDateTest {
		public void setDate(Date date);

		public void setCalendar(Calendar cal);
	}

	private static class TestAppClaz {
		private IDateTest idate;

		public void setTime(Date date, Calendar cal) {
			idate.setDate(date);
			idate.setCalendar(cal);
		}

		public void setIdate(IDateTest idate) {
			this.idate = idate;
		}
	}
}
