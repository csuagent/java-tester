package org.jtester.dbfit.environment.typesmap;

import java.util.HashMap;
import java.util.Map;

public interface DB2TypeMap {
	public final static Map<String, Class<?>> java = new HashMap<String, Class<?>>() {
		private static final long serialVersionUID = 2634796890699526702L;

		{
		}
	};

	public final static Map<String, Integer> sql = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 458255708680362751L;

		{
		}
	};
}
