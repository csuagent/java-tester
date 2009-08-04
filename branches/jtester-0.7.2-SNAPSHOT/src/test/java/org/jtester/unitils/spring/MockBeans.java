package org.jtester.unitils.spring;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jtester.unitils.jmock.MockBeanByName;
import org.unitils.util.AnnotationUtils;

public class MockBeans {
	private static Map<Long, Map<String, Object>> mockByNames = new ConcurrentHashMap<Long, Map<String, Object>>();

	/**
	 * 注册需要mock的bean对象
	 * 
	 * @param beanName
	 * @param bean
	 */
	public static void addMockBeanByName(String beanName, Object bean) {
		Map<String, Object> map = byNameBeanMap();
		map.put(beanName, bean);
	}

	/**
	 * 清除所有已注册的mock bean
	 */
	public static void clean() {
		Map<String, Object> map = byNameBeanMap();
		map.clear();
	}

	/**
	 * 判断是否已经注册了MockBean
	 * 
	 * @return
	 */
	public static boolean hasRegisteredMockBean() {
		Map<String, Object> map = byNameBeanMap();
		return map.size() > 0;
	}

	/**
	 * 测试类中是否有@MockBean的变量
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean hasMockBean(Class<?> clazz) {
		Set<Field> fields = AnnotationUtils.getFieldsAnnotatedWith(clazz, MockBeanByName.class);
		return fields.size() > 0;
	}

	/**
	 * 获得的mock的bean对象
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBeanByName(String beanName) {
		Map<String, Object> map = byNameBeanMap();
		return map.get(beanName);
	}

	private static Map<String, Object> byNameBeanMap() {
		long currThreadID = Thread.currentThread().getId();
		Map<String, Object> map = mockByNames.get(currThreadID);
		if (map == null) {
			map = new HashMap<String, Object>();
			mockByNames.put(currThreadID, map);
		}
		return map;
	}
}
