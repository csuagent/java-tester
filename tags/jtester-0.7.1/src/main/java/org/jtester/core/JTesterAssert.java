package org.jtester.core;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.jtester.hamcrest.iassert.object.impl.ArrayAssert;
import org.jtester.hamcrest.iassert.object.impl.BooleanAssert;
import org.jtester.hamcrest.iassert.object.impl.ByteAssert;
import org.jtester.hamcrest.iassert.object.impl.CalendarAssert;
import org.jtester.hamcrest.iassert.object.impl.CharacterAssert;
import org.jtester.hamcrest.iassert.object.impl.CollectionAssert;
import org.jtester.hamcrest.iassert.object.impl.DoubleAssert;
import org.jtester.hamcrest.iassert.object.impl.FileAssert;
import org.jtester.hamcrest.iassert.object.impl.FloatAssert;
import org.jtester.hamcrest.iassert.object.impl.IntegerAssert;
import org.jtester.hamcrest.iassert.object.impl.LongAssert;
import org.jtester.hamcrest.iassert.object.impl.MapAssert;
import org.jtester.hamcrest.iassert.object.impl.ObjectAssert;
import org.jtester.hamcrest.iassert.object.impl.ShortAssert;
import org.jtester.hamcrest.iassert.object.impl.StringAssert;
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
import org.jtester.hamcrest.iassert.object.intf.IObjectAssert;
import org.jtester.hamcrest.iassert.object.intf.IShortAssert;
import org.jtester.hamcrest.iassert.object.intf.IStringAssert;
import org.jtester.utility.ArrayConvertor;

/**
 * asserter's factory
 * 
 * @author darui.wudr
 * 
 */
public interface JTesterAssert {
	public static final Want want = new Want();

	public static class Want {
		/**
		 * 字符串断言
		 * 
		 * @param value
		 *            字符串变量
		 * @return
		 */
		public IStringAssert string(String value) {
			return new StringAssert(value);
		}

		/**
		 * 布尔变量断言
		 * 
		 * @param value
		 *            a boolean variable
		 * @return
		 */
		public IBooleanAssert bool(Boolean value) {
			return new BooleanAssert(value);
		}

		/**
		 * integer变量断言
		 * 
		 * @param value
		 *            a integer variable
		 * @return
		 */
		public IIntegerAssert number(Integer value) {
			return new IntegerAssert(value);
		}

		/**
		 * short变量断言
		 * 
		 * @param value
		 *            a short variable
		 * @return
		 */
		public IShortAssert number(Short value) {
			return new ShortAssert(value);
		}

		/**
		 * long变量断言
		 * 
		 * @param value
		 *            a long variable
		 * @return
		 */
		public ILongAssert number(Long value) {
			return new LongAssert(value);
		}

		/**
		 * double变量断言
		 * 
		 * @param value
		 *            a double variable
		 * @return
		 */
		public IDoubleAssert number(Double value) {
			return new DoubleAssert(value);
		}

		/**
		 * float变量断言
		 * 
		 * @param value
		 *            a float variable
		 * @return
		 */
		public IFloatAssert number(Float value) {
			return new FloatAssert(value);
		}

		/**
		 * char变量断言
		 * 
		 * @param value
		 *            a character variable
		 * @return
		 */
		public ICharacterAssert character(Character value) {
			return new CharacterAssert(value);
		}

		/**
		 * byte变量断言
		 * 
		 * @param value
		 *            a byte variable
		 * @return
		 */
		public IByteAssert bite(Byte value) {
			return new ByteAssert(value);
		}

		/**
		 * 数组变量断言
		 * 
		 * @param value
		 *            a array variable
		 * @return
		 */
		public <T extends Object> IArrayAssert array(T value[]) {
			return new ArrayAssert(value);
		}

		/**
		 * 布尔值数组变量断言
		 * 
		 * @param value
		 *            a boolean array
		 * @return
		 */
		public IArrayAssert array(boolean value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * byte数组变量断言
		 * 
		 * @param value
		 *            a byte array
		 * @return
		 */
		public IArrayAssert array(byte value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * character数组变量断言
		 * 
		 * @param value
		 *            a character array
		 * @return
		 */
		public IArrayAssert array(char value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * short数值类型数组变量断言
		 * 
		 * @param value
		 *            a short array
		 * @return
		 */
		public IArrayAssert array(short value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * integer数值类型数组变量断言
		 * 
		 * @param value
		 *            a integer array
		 * @return
		 */
		public IArrayAssert array(int value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * long数值类型数组变量断言
		 * 
		 * @param value
		 *            a long array
		 * @return
		 */
		public IArrayAssert array(long value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * float数值类型数组变量断言
		 * 
		 * @param value
		 *            a float array
		 * @return
		 */
		public IArrayAssert array(float value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * double数值类型数组变量断言
		 * 
		 * @param value
		 *            a double array
		 * @return
		 */
		public IArrayAssert array(double value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * map变量断言
		 * 
		 * @param value
		 *            a map argument
		 * @return
		 */
		public IMapAssert map(Map<?, ?> map) {
			return new MapAssert(map);
		}

		/**
		 * collection变量断言
		 * 
		 * @param value
		 *            a collection argument
		 * @return
		 */
		public ICollectionAssert collection(Collection<?> collection) {
			return new CollectionAssert(collection);
		}

		/**
		 * 通用object对象断言
		 * 
		 * @param value
		 *            a object argument
		 * @return
		 */
		public IObjectAssert object(Object bean) {
			return new ObjectAssert(bean);
		}

		/**
		 * 一个永远失败的断言
		 */
		public void fail() {
			assert true == false;
		}

		/**
		 * 一个永远失败的断言
		 * 
		 * @param message
		 *            失败后的提示信息
		 */
		public void fail(String message) {
			assert true == false : message;
		}

		/**
		 * a file argument asserter
		 * 
		 * @param filename
		 * @return
		 */
		public IFileAssert file(String filename) {
			File file = new File(filename);
			return new FileAssert(file);
		}

		/**
		 * 文件变量断言
		 * 
		 * @param file
		 * @return
		 */
		public IFileAssert file(File file) {
			return new FileAssert(file);
		}

		/**
		 * calendar变量断言
		 * 
		 * @param cal
		 * @return
		 */
		public ICalendarAssert<Calendar> calendar(Calendar cal) {
			return new CalendarAssert<Calendar>(cal, Calendar.class);
		}

		/**
		 * 日期变量断言
		 * 
		 * @param date
		 * @return
		 */
		public ICalendarAssert<Date> date(Date date) {
			return new CalendarAssert<Date>(date, Date.class);
		}

		static {
			// Depreciate
			// /**
			// * 布尔变量断言
			// *
			// * @param value
			// * a boolean variable
			// * @return
			// */
			// public IBooleanAssert bool(boolean value) {
			// return new BooleanAssert(value);
			// }
			// /**
			// * char变量断言
			// *
			// * @param value
			// * a character variable
			// * @return
			// */
			// public ICharacterAssert character(char value) {
			// return new CharacterAssert(value);
			// }
			// /**
			// * byte变量断言
			// *
			// * @param value
			// * a byte variable
			// * @return
			// */
			// public IByteAssert bite(byte value) {
			// return new ByteAssert(value);
			// }
		}
	}
}
