package org.jtester.jmock;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;

public class ExpectationsUtil {
	private static Map<Long, Expectations> jes = new HashMap<Long, Expectations>();

	public static void register(Expectations ex) {
		jes.put(Thread.currentThread().getId(), ex);
	}

	public static Expectations getExpectations(long id) {
		Expectations ex = jes.get(id);
		if (ex == null) {
			throw new RuntimeException("no expectation has been defined by Thread");
		}
		return ex;
	}
}
