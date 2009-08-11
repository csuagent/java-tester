package org.jtester.utility;

public class StringUtil {
	public static boolean isBlankOrNull(String in) {
		if (in == null) {
			return true;
		} else if (in.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
