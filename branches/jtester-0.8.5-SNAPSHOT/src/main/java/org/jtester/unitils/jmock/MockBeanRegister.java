package org.jtester.unitils.jmock;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jtester.utility.StringUtil;
import org.unitils.util.AnnotationUtils;

public class MockBeanRegister {
	private static Map<Long, Map<String, Object>> mockByNames = new ConcurrentHashMap<Long, Map<String, Object>>();
	private static Map<Long, Map<Class<?>, Object>> mockByTypes = new ConcurrentHashMap<Long, Map<Class<?>, Object>>();

	private static Map<Long, Map<String, Class<?>>> nameRegister = new ConcurrentHashMap<Long, Map<String, Class<?>>>();
	private static Map<Long, Set<Class<?>>> typeRegister = new ConcurrentHashMap<Long, Set<Class<?>>>();

	/**
	 * 注册需要mock的bean对象
	 * 
	 * @param beanName
	 * @param bean
	 */
	public static void addMockBean(String beanName, Object bean) {
		Map<String, Object> map = currThreadByNameMocks();
		map.put(beanName, bean);
	}

	public static void addMockBean(Class<?> beanClaz, Object bean) {
		Map<Class<?>, Object> map = currThreadByTypeMocks();
		map.put(beanClaz, bean);
	}

	public static void register(Class<?> type, String name) {
		if (StringUtil.isBlankOrNull(name) == false) {
			currThreadRegistedByName().put(name, type);
		} else {
			currThreadRegistedByType().add(type);
		}
	}

	/**
	 * 清除所有已注册的mock bean
	 */
	public static void cleanMockBean() {
		currThreadByNameMocks().clear();
		currThreadByNameMocks().clear();
	}

	/**
	 * 清除注册信息
	 */
	public static void cleanRegister() {
		currThreadRegistedByName().clear();
		currThreadRegistedByType().clear();
	}

	/**
	 * 判断是否已经注册了MockBean
	 * 
	 * @return
	 */
	public static boolean hasRegisteredMockBean() {
		Map<String, Class<?>> byNames = currThreadRegistedByName();
		Set<Class<?>> byTypes = currThreadRegistedByType();
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

	public static Class<?> hasRegisted(String name, Class<?> type) {
		Map<String, Class<?>> byNames = currThreadRegistedByName();
		if (byNames.containsKey(name)) {
			return byNames.get(name);
		}
		Set<Class<?>> byTypes = currThreadRegistedByType();
		if (byTypes.contains(type)) {
			return type;
		} else {
			return null;
		}
	}

	public static boolean hasRegisted(String name) {
		Map<String, Class<?>> byNames = currThreadRegistedByName();
		return byNames.containsKey(name);
	}

	/**
	 * 获得的mock的bean对象
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName, Class<?> type) {
		Map<String, Object> map = currThreadByNameMocks();
		Object bean = map.get(beanName);
		if (bean == null) {
			return currThreadByTypeMocks().get(type);
		} else {
			return bean;
		}
	}

	private static Map<String, Object> currThreadByNameMocks() {
		long currThreadID = Thread.currentThread().getId();
		Map<String, Object> map = mockByNames.get(currThreadID);
		if (map == null) {
			map = new HashMap<String, Object>();
			mockByNames.put(currThreadID, map);
		}
		return map;
	}

	private static Map<Class<?>, Object> currThreadByTypeMocks() {
		long currThreadID = Thread.currentThread().getId();
		Map<Class<?>, Object> map = mockByTypes.get(currThreadID);
		if (map == null) {
			map = new HashMap<Class<?>, Object>();
			mockByTypes.put(currThreadID, map);
		}
		return map;
	}

	private static Map<String, Class<?>> currThreadRegistedByName() {
		long currThreadID = Thread.currentThread().getId();
		Map<String, Class<?>> map = nameRegister.get(currThreadID);
		if (map == null) {
			map = new HashMap<String, Class<?>>();
			nameRegister.put(currThreadID, map);
		}
		return map;
	}

	private static Set<Class<?>> currThreadRegistedByType() {
		long currThreadID = Thread.currentThread().getId();
		Set<Class<?>> set = typeRegister.get(currThreadID);
		if (set == null) {
			set = new HashSet<Class<?>>();
			typeRegister.put(currThreadID, set);
		}
		return set;
	}
}
