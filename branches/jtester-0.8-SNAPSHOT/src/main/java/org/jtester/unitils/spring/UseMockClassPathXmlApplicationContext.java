package org.jtester.unitils.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link ClassPathXmlApplicationContext}的子类，运行使用@MockBean来替代spring中加载的bean值
 */
public class UseMockClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {
	private class MyDefaultListableBeanFactory extends DefaultListableBeanFactory {
		public MyDefaultListableBeanFactory(BeanFactory parentBeanFactory) {
			super(parentBeanFactory);
		}

		@Override
		@SuppressWarnings("unchecked")
		public Object getBean(final String name, final Class requiredType, final Object[] args) throws BeansException {
			Object bean = singletonsByBeanName.get(name);
			System.out.println("get mock bean");
			return bean == null ? super.getBean(name, requiredType, args) : bean;
		}
	}

	private final Map<String, Object> singletonsByBeanName = new HashMap<String, Object>();

	public UseMockClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent,
			Map<String, Object> singletonsByBeanName) throws BeansException {
		super(configLocations, false, parent);
		if (singletonsByBeanName != null)
			this.singletonsByBeanName.putAll(singletonsByBeanName);
		if (refresh)
			refresh();
	}

	@Override
	protected DefaultListableBeanFactory createBeanFactory() {
		return new MyDefaultListableBeanFactory(getInternalParentBeanFactory());
	}
}