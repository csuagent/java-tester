package org.jtester.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	public static <T> void serialize(T o, String filename) {
		SerializeUtil.mkdirs(filename);

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(o);
			out.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T deSerialize(Class<T> claz, String filename) {
		File file = SerializeUtil.isFileExisted(filename);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			Object obj = in.readObject();
			in.close();
			return (T) obj;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static void mkdirs(String filename) {
		filename = filename.replace('/', File.separatorChar);
		filename = filename.replace('\\', File.separatorChar);
		File fo = new File(filename);
		// 文件不存在,就创建该文件
		if (!fo.exists()) {
			// 先创建文件的目录
			String path = filename.substring(0, filename.lastIndexOf(File.separatorChar));
			File pFile = new File(path);
			pFile.mkdirs();
		}
	}

	private static File isFileExisted(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			throw new RuntimeException("object serializable file doesn't exist");
		}
		return file;
	}
}
