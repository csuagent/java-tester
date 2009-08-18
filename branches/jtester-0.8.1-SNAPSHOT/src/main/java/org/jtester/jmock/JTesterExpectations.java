package org.jtester.jmock;

import java.io.File;
import java.util.Collection;

import org.jmock.Expectations;
import org.jmock.api.Action;
import org.jtester.utility.ClazzUtil;
import org.jtester.utility.SerializeUtil;

public abstract class JTesterExpectations extends Expectations implements ICallMethod {
	public JTesterExpectations() {
		ExpectationsUtil.register(this);
	}

	public JExpections will = new JExpections(this);

	public static class JExpections {

		public JExpections(JTesterExpectations expectations) {
			this.expectations = expectations;
			this.call = expectations;
			this.returns = new ReturnValue(expectations);
		}

		private JTesterExpectations expectations;

		public ICallMethod call;

		public ReturnValue returns;

		/**
		 * 希望行为发生中抛出异常
		 * 
		 * @param throwable
		 */
		public void throwException(Throwable throwable) {
			expectations.will(Expectations.throwException(throwable));
		}

		/**
		 * 希望行为发生中抛出异常,(异常的值从xml中反序列化回来)
		 * 
		 * @param exceptionClazz
		 *            异常的类型class
		 * @param xmlUrl
		 *            xml文件的url
		 */
		public void throwException(Class<? extends Throwable> exceptionClazz, String xmlUrl) {
			Throwable o = SerializeUtil.fromXML(exceptionClazz, xmlUrl);
			expectations.will(Expectations.throwException(o));
		}

		/**
		 * 希望行为发生中抛出异常,(异常的值从xml中反序列化回来)
		 * 
		 * @param exceptionClazz
		 *            异常的类型class
		 * @param pathClazz
		 *            xml文件所在路径的class，用来方便定位xml的classpath路径
		 * @param xmlname
		 *            xml文件的名称
		 */
		public void throwException(Class<? extends Throwable> exceptionClazz, Class<?> pathClazz, String xmlname) {
			String xmlUrl = ClazzUtil.getPathFromPath(pathClazz) + File.separatorChar + xmlname;
			this.throwException(exceptionClazz, xmlUrl);
		}

		public void doAll(Action... actions) {
			expectations.will(Expectations.doAll(actions));
		}

		public void onConsecutiveCalls(Action... actions) {
			expectations.will(Expectations.onConsecutiveCalls(actions));
		}
	}

	public static class ReturnValue {
		private Expectations expectations;

		public ReturnValue(Expectations expectations) {
			this.expectations = expectations;
		}

		public void value(Object result) {
			expectations.will(Expectations.returnValue(result));
		}

		/**
		 * 行为（api）的返回值,(从指定的xml中反序列化回来)
		 * 
		 * @param returnClazz
		 *            要返回对象的类型
		 * @param xmlUrl
		 *            反序列化的xml文件名称(包含classpath路径)
		 */
		public void value(Class<?> returnClazz, String xmlUrl) {
			Object o = SerializeUtil.fromXML(returnClazz, xmlUrl);
			this.value(o);
		}

		/**
		 * 行为（api）的返回值,(从指定的xml中反序列化回来)
		 * 
		 * @param returnClazz
		 *            要返回对象的类型
		 * @param pathClazz
		 *            xml文件所在的class，用来方便定位xml文件路径
		 * @param xmlUrl
		 *            反序列化的xml文件名称
		 */
		public void value(Class<?> returnClazz, Class<?> pathClazz, String xmlUrl) {
			String path = ClazzUtil.getPathFromPath(pathClazz);
			Object o = SerializeUtil.fromXML(returnClazz, path + File.separatorChar + xmlUrl);
			this.value(o);
		}

		public void iterator(Collection<?> collection) {
			expectations.will(Expectations.returnIterator(collection));
		}

		public <T> void iterator(T... items) {
			expectations.will(Expectations.returnIterator(items));
		}

		public void enumeration(Collection<?> collection) {
			expectations.will(Expectations.returnEnumeration(collection));
		}

		public <T> void enumeration(T... items) {
			expectations.will(Expectations.returnEnumeration(items));
		}
	}
}
