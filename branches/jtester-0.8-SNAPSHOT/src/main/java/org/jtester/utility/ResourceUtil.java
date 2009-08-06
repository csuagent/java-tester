package org.jtester.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceUtil {
	/*
	 * To convert the InputStream to String we use the BufferedReader.readLine()
	 * method. We iterate until the BufferedReader return null which means
	 * there's no more data to read. Each line will appended to a StringBuilder
	 * and returned as String.
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder buffer = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return buffer.toString();
	}

	public static void copyFile(String classPathFile, File outputFile) {
		InputStream is = ClassLoader.getSystemResourceAsStream(classPathFile);
		String content = convertStreamToString(is);
		try {
			FileWriter writer = new FileWriter(outputFile);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static InputStream findClassPathStream(Class<?> claz, String url) {
		InputStream is = ClassLoader.getSystemResourceAsStream(url);
		if (is != null) {
			return is;
		} else if (claz == null) {
			throw new RuntimeException("can't find class path resource in " + url);
		}
		String newUrl = ClazzUtil.getPathFromPath(claz) + File.separatorChar + url;
		is = ClassLoader.getSystemResourceAsStream(newUrl);
		if (is != null) {
			return is;
		} else {
			throw new RuntimeException("can't find class path resource in " + url + " or in " + newUrl);
		}
	}
}