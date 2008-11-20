package org.jtester.core;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IAssertOptions {
	public static class options {
		public static class RefCompMode {
			public final static ReflectionComparatorMode IGNORE_DEFAULTS = ReflectionComparatorMode.IGNORE_DEFAULTS;

			public final static ReflectionComparatorMode LENIENT_DATES = ReflectionComparatorMode.LENIENT_DATES;

			public final static ReflectionComparatorMode LENIENT_ORDER = ReflectionComparatorMode.LENIENT_ORDER;
		}
	}
}
