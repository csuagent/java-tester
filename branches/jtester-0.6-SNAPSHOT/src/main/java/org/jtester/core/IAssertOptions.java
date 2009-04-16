package org.jtester.core;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IAssertOptions {
	/**
	 * reflection assert modes
	 */
	public static final ReflectionOptions opts = new ReflectionOptions();
	public static final ReflectionOptions options = new ReflectionOptions();

	public static class ReflectionOptions {
		/**
		 * Ignore fields that do not have a default value for the left-hand
		 * (expected) side
		 */
		public final ReflectionComparatorMode IGNORE_DEFAULTS = ReflectionComparatorMode.IGNORE_DEFAULTS;
		/**
		 * Do not compare the actual time/date value, just that both left-hand
		 * (expected) and right-hand side are null or not null.
		 */
		public final ReflectionComparatorMode LENIENT_DATES = ReflectionComparatorMode.LENIENT_DATES;
		/**
		 * Do not compare the order of collections and arrays. Only check that
		 * all values of the left-hand (expected) side collection or array are
		 * also contained in the right-hand (actual) side and vice versa.
		 */
		public final ReflectionComparatorMode LENIENT_ORDER = ReflectionComparatorMode.LENIENT_ORDER;
	}
}
