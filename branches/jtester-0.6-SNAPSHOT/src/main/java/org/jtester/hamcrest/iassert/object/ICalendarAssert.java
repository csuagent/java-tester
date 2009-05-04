package org.jtester.hamcrest.iassert.object;

import java.util.Calendar;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

/**
 * 日历类型对象断言接口
 * 
 * @author darui.wudr
 * 
 */
public interface ICalendarAssert extends IBaseAssert<Calendar, ICalendarAssert> {
	/**
	 * 日历值的年等于期望值
	 * 
	 * @param year
	 *            期望值
	 * @return
	 */
	public ICalendarAssert yearIs(int year);

	/**
	 * 日历值的年等于期望值
	 * 
	 * @param year
	 *            期望值
	 * @return
	 */
	public ICalendarAssert yearIs(String year);

	/**
	 * 日历值的月份等于期望值
	 * 
	 * @param month
	 *            期望值
	 * @return
	 */
	public ICalendarAssert monthIs(int month);

	/**
	 * 日历值的月份等于期望值
	 * 
	 * @param month
	 *            期望值
	 * @return
	 */
	public ICalendarAssert monthIs(String month);

	/**
	 * 日历值的日期等于期望值
	 * 
	 * @param day
	 *            期望值
	 * @return
	 */
	public ICalendarAssert dayIs(int day);

	/**
	 * 日历值的日期等于期望值
	 * 
	 * @param day
	 *            期望值
	 * @return
	 */
	public ICalendarAssert dayIs(String day);

	/**
	 * 日历值的小时(24小时制)等于期望值
	 * 
	 * @param hour
	 *            期望值
	 * @return
	 */
	public ICalendarAssert hourIs(int hour);

	/**
	 * 日历值的小时(24小时制)等于期望值
	 * 
	 * @param hour
	 *            期望值
	 * @return
	 */
	public ICalendarAssert hourIs(String hour);

	/**
	 * 日历值的分钟等于期望值
	 * 
	 * @param minute
	 *            期望值
	 * @return
	 */
	public ICalendarAssert minuteIs(int minute);

	/**
	 * 日历值的分钟等于期望值
	 * 
	 * @param minute
	 *            期望值
	 * @return
	 */
	public ICalendarAssert minuteIs(String minute);

	/**
	 * 日历值的秒等于期望值
	 * 
	 * @param second
	 *            期望值
	 * @return
	 */
	public ICalendarAssert secondIs(int second);

	/**
	 * 日历值的秒等于期望值
	 * 
	 * @param second
	 *            期望值
	 * @return
	 */
	public ICalendarAssert secondIs(String second);
}
