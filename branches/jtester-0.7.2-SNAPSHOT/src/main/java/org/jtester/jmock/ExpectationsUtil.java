package org.jtester.jmock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jmock.Expectations;
import org.jtester.exception.JTesterException;

/**
 * 用于注册或获取当前线程下当前使用的org.jmock.Expectations实例<br>
 * 用于expectation参数断言调用org.jtester.hamcrest.iassert.common.intf.IAssert.wanted( )
 * 时,向当前的expectation实例注册期望发生的方法等
 * 
 * @author darui.wudr
 * 
 */
public class ExpectationsUtil {
	private static Map<Long, Expectations> jes = new ConcurrentHashMap<Long, Expectations>();

	/**
	 * 注册当前线程下当前使用的org.jmock.Expectations实例
	 * 
	 * @param ex
	 */
	public static void register(Expectations ex) {
		jes.put(Thread.currentThread().getId(), ex);
	}

	/**
	 * 获取当前线程下当前使用的org.jmock.Expectations实例
	 * 
	 * @param id
	 * @return
	 */
	public static Expectations getExpectations(long id) {
		Expectations ex = jes.get(id);
		if (ex == null) {
			throw new JTesterException("no expectation has been defined by Thread");
		}
		return ex;
	}
}
