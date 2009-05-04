package org.jtester.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * POJO对象序列化和反序列化工具类
 * 
 * @author darui.wudr
 * 
 */
public class SerializeUtil {
	/**
	 * 将pojo序列化后存储在dat类型的文件中
	 * 
	 * @param <T>
	 * @param o
	 *            需要序列化的对象
	 * @param filename
	 *            存储文件的路径名称
	 */
	public static <T> void toDat(T o, String filename) {
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

	/**
	 * 利用XStream将pojo保存为xml格式的文件
	 * 
	 * @param <T>
	 * @param o
	 *            需要序列化的对象
	 * @param filename
	 *            存储文件的路径名称
	 */
	public static <T> void toXML(T o, String filename) {
		try {
			XStream xs = new XStream(new DomDriver());
			// XStream xs = new XStream();
			FileOutputStream fos = new FileOutputStream(filename);
			xs.toXML(o, fos);
			System.out.println(xs.toXML(o));
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从dat文件中将pojo反序列化出来
	 * 
	 * @param <T>
	 * @param claz
	 *            反序列化出来的pojo class类型
	 * @param filename
	 *            pojo序列化信息文件,如果以"classpath:"开头表示文件存储在classpth的package路径下，否则表示文件的绝对路径
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromDat(Class<T> claz, String filename) {
		try {
			InputStream inputStream = SerializeUtil.isFileExisted(filename);
			ObjectInputStream in = new ObjectInputStream(inputStream);
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

	/**
	 * 利用xstream将pojo从xml文件中反序列化出来
	 * 
	 * @param <T>
	 * @param claz
	 *            反序列化出来的pojo class类型
	 * @param filename
	 *            pojo序列化信息文件,如果以"classpath:"开头表示文件存储在classpth的package路径下，否则表示文件的绝对路径
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXML(Class<T> claz, String filename) {
		try {
			InputStream fis = SerializeUtil.isFileExisted(filename);
			if (fis == null) {
				throw new RuntimeException(String.format("file '%s' doesn't exist", filename));
			}
			XStream xs = new XStream(new DomDriver());
			// XStream xs = new XStream();
			Object o = xs.fromXML(fis);
			return (T) o;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static InputStream isFileExisted(String filename) throws FileNotFoundException {
		if (filename.startsWith("classpath:")) {
			String file = filename.replaceFirst("classpath:", "");
			return ClassLoader.getSystemResourceAsStream(file);
		} else {
			File file = new File(filename);
			if (!file.exists()) {
				throw new RuntimeException("object serializable file doesn't exist");
			}
			return new FileInputStream(file);
		}
	}

	private static void mkdirs(String filename) {
		filename = filename.replace('/', File.separatorChar);
		filename = filename.replace('\\', File.separatorChar);
		File fo = new File(filename);
		// 文件不存在,就创建该文件,先创建文件的目录
		if (!fo.exists()) {
			String path = filename.substring(0, filename.lastIndexOf(File.separatorChar));
			File pFile = new File(path);
			pFile.mkdirs();
		}
	}
}
