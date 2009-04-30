package org.jtester.core;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.jtester.hamcrest.iassert.object.IArrayAssert;
import org.jtester.hamcrest.iassert.object.IBooleanAssert;
import org.jtester.hamcrest.iassert.object.IByteAssert;
import org.jtester.hamcrest.iassert.object.ICalendarAssert;
import org.jtester.hamcrest.iassert.object.ICharacterAssert;
import org.jtester.hamcrest.iassert.object.ICollectionAssert;
import org.jtester.hamcrest.iassert.object.IDoubleAssert;
import org.jtester.hamcrest.iassert.object.IFileAssert;
import org.jtester.hamcrest.iassert.object.IFloatAssert;
import org.jtester.hamcrest.iassert.object.IIntegerAssert;
import org.jtester.hamcrest.iassert.object.ILongAssert;
import org.jtester.hamcrest.iassert.object.IMapAssert;
import org.jtester.hamcrest.iassert.object.IObjectAssert;
import org.jtester.hamcrest.iassert.object.IShortAssert;
import org.jtester.hamcrest.iassert.object.IStringAssert;
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
		 * a string argument asserter
		 * 
		 * @param value
		 *            a string variable
		 * @return
		 */
		public IStringAssert string(String value) {
			return new StringAssert(value);
		}

		/**
		 * a boolean argument asserter
		 * 
		 * @param value
		 *            a boolean variable
		 * @return
		 */
		public IBooleanAssert bool(boolean value) {
			return new BooleanAssert(value);
		}

		/**
		 * a boolean argument asserter
		 * 
		 * @param value
		 *            a boolean variable
		 * @return
		 */
		public IBooleanAssert bool(Boolean value) {
			return new BooleanAssert(value);
		}

		/**
		 * an integer argument asserter
		 * 
		 * @param value
		 *            a integer variable
		 * @return
		 */
		public IIntegerAssert number(Integer value) {
			return new IntegerAssert(value);
		}

		/**
		 * a short argument asserter
		 * 
		 * @param value
		 *            a short variable
		 * @return
		 */
		public IShortAssert number(Short value) {
			return new ShortAssert(value);
		}

		/**
		 * a long argument asserter
		 * 
		 * @param value
		 *            a long variable
		 * @return
		 */
		public ILongAssert number(Long value) {
			return new LongAssert(value);
		}

		/**
		 * a double argument asserter
		 * 
		 * @param value
		 *            a double variable
		 * @return
		 */
		public IDoubleAssert number(Double value) {
			return new DoubleAssert(value);
		}

		/**
		 * a float argument asserter
		 * 
		 * @param value
		 *            a float variable
		 * @return
		 */
		public IFloatAssert number(Float value) {
			return new FloatAssert(value);
		}

		/**
		 * a character argument asserter
		 * 
		 * @param value
		 *            a character variable
		 * @return
		 */
		public ICharacterAssert character(Character value) {
			return new CharacterAssert(value);
		}

		/**
		 * a character argument asserter
		 * 
		 * @param value
		 *            a character variable
		 * @return
		 */
		public ICharacterAssert character(char value) {
			return new CharacterAssert(value);
		}

		/**
		 * a byte argument asserter
		 * 
		 * @param value
		 *            a byte variable
		 * @return
		 */
		public IByteAssert bite(Byte value) {
			return new ByteAssert(value);
		}

		/**
		 * a byte argument asserter
		 * 
		 * @param value
		 *            a byte variable
		 * @return
		 */
		public IByteAssert bite(byte value) {
			return new ByteAssert(value);
		}

		/**
		 * an array argument asserter
		 * 
		 * @param value
		 *            a array variable
		 * @return
		 */
		public <T extends Object> IArrayAssert array(T value[]) {
			return new ArrayAssert(value);
		}

		/**
		 * a boolean array argument asserter
		 * 
		 * @param value
		 *            a boolean array
		 * @return
		 */
		public IArrayAssert array(boolean value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a byte array argument asserter
		 * 
		 * @param value
		 *            a byte array
		 * @return
		 */
		public IArrayAssert array(byte value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a character array argument asserter
		 * 
		 * @param value
		 *            a character array
		 * @return
		 */
		public IArrayAssert array(char value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a short array argument asserter
		 * 
		 * @param value
		 *            a short array
		 * @return
		 */
		public IArrayAssert array(short value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * an integer array argument asserter
		 * 
		 * @param value
		 *            a integer array
		 * @return
		 */
		public IArrayAssert array(int value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a long array argument asserter
		 * 
		 * @param value
		 *            a long array
		 * @return
		 */
		public IArrayAssert array(long value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a float array argument asserter
		 * 
		 * @param value
		 *            a float array
		 * @return
		 */
		public IArrayAssert array(float value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a double array argument asserter
		 * 
		 * @param value
		 *            a double array
		 * @return
		 */
		public IArrayAssert array(double value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		/**
		 * a map argument asserter
		 * 
		 * @param value
		 *            a map argument
		 * @return
		 */
		public IMapAssert map(Map<?, ?> map) {
			return new MapAssert(map);
		}

		/**
		 * a collection argument asserter
		 * 
		 * @param value
		 *            a collection argument
		 * @return
		 */
		public ICollectionAssert collection(Collection<?> collection) {
			return new CollectionAssert(collection);
		}

		/**
		 * an object argument asserter
		 * 
		 * @param value
		 *            a object argument
		 * @return
		 */
		public IObjectAssert object(Object bean) {
			return new ObjectAssert(bean);
		}

		/**
		 * an assert statement always throw an AssertError exception
		 */
		public void fail() {
			assert true == false;
		}

		/**
		 * an assert statement always throw an AssertError exception
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
		 * a file argument asserter
		 * 
		 * @param file
		 * @return
		 */
		public IFileAssert file(File file) {
			return new FileAssert(file);
		}

		/**
		 * a calendar argument asserter
		 * 
		 * @param cal
		 * @return
		 */
		public ICalendarAssert date(Calendar cal) {
			return new CalendarAssert(cal);
		}

		/**
		 * a date argument asserter
		 * 
		 * @param date
		 * @return
		 */
		public ICalendarAssert date(Date date) {
			Calendar cal = null;
			if (date != null) {
				cal = Calendar.getInstance();
				cal.setTime(date);
			}
			return new CalendarAssert(cal);
		}
	}
}
