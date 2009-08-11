package org.jtester.unitils.jmock;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.unitils.util.AnnotationUtils;

public class MockBeanRegister {
	private static Map<Long, Map<String, Object>> mockByNames = new ConcurrentHashMap<Long, Map<String, Object>>();
	private static Map<Long, Map<Class<?>, Object>> mockByTypes = new ConcurrentHashMap<Long, Map<Class<?>, Object>>();

	/**
	 * 注册需要mock的bean对象
	 * 
	 * @param beanName
	 * @param bean
	 */
	public static void addMockBean(String beanName, Object bean) {
		Map<String, Object> map = byNameRegister();
		map.put(beanName, bean);
	}

	public static void addMockBean(Class<?> beanClaz, Object bean) {
		Map<Class<?>, Object> map = byTypeRegister();
		map.put(beanClaz, bean);
	}

	/**
	 * 清除所有已注册的mock bean
	 */
	public static void clean() {
		Map<String, Object> byNames = byNameRegister();
		byNames.clear();
		Map<String, Object> byTypes = byNameRegister();
		byTypes.clear();
	}

	/**
	 * 判断是否已经注册了MockBean
	 * 
	 * @return
	 */
	public static boolean hasRegisteredMockBean() {
		Map<String, Object> byNames = byNameRegister();
		Map<Class<?>, Object> byTypes = byTypeRegister();
		return byNames.size() > 0 || byTypes.size() > 0;
	}

	/**
	 * 测试类中是否有@MockBean的变量
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean hasMockBean(Class<?> clazz) {
		Set<Field> fields = AnnotationUtils.getFieldsAnnotatedWith(clazz, MockBean.class);
		return fields.size() > 0;
	}

	/**
	 * 获得的mock的bean对象
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBeanByName(String beanName) {
		Map<String, Object> map = byNameRegister();
		return map.get(beanName);
	}

	public static Object getBeanByType(Class<?> beanClaz) {
		Map<Class<?>, Object> map = byTypeRegister();
		return map.get(beanClaz);
	}

	private static Map<String, Object> byNameRegister() {
		long currThreadID = Thread.currentThread().getId();
		Map<String, Object> map = mockByNames.get(currThreadID);
		if (map == null) {
			map = new HashMap<String, Object>();
			mockByNames.put(currThreadID, map);
		}
		return map;
	}

	private static Map<Class<?>, Object> byTypeRegister() {
		long currThreadID = Thread.currentThread().getId();
		Map<Class<?>, Object> map = mockByTypes.get(currThreadID);
		if (map == null) {
			map = new HashMap<Class<?>, Object>();
			mockByTypes.put(currThreadID, map);
		}
		return map;
	}
}
