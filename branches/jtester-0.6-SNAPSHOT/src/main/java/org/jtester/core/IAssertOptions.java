package org.jtester.core;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IAssertOptions {
	public static class opts {
		public static class CompMode {
			public final static ReflectionComparatorMode IGNORE_DEFAULTS = ReflectionComparatorMode.IGNORE_DEFAULTS;

			public final static ReflectionComparatorMode LENIENT_DATES = ReflectionComparatorMode.LENIENT_DATES;

			public final static ReflectionComparatorMode LENIENT_ORDER = ReflectionComparatorMode.LENIENT_ORDER;
		}
	}

	public static class options extends opts {

	}
}
