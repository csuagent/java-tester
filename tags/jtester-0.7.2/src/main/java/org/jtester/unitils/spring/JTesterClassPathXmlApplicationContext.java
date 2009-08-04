package org.jtester.unitils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link ClassPathXmlApplicationContext}的子类，运行使用@MockBean来替代spring中加载的bean值
 */
public class JTesterClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {
	private class MyDefaultListableBeanFactory extends DefaultListableBeanFactory {
		public MyDefaultListableBeanFactory(BeanFactory parentBeanFactory) {
			super(parentBeanFactory);
		}

		@Override
		@SuppressWarnings("unchecked")
		public Object getBean(final String name, final Class requiredType, final Object[] args) throws BeansException {
			Object bean = MockBeans.getBeanByName(name);
			return bean == null ? super.getBean(name, requiredType, args) : bean;
		}
	}

	public JTesterClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
			throws BeansException {
		super(configLocations, false, parent);
		if (refresh) {
			refresh();
		}
	}

	@Override
	protected DefaultListableBeanFactory createBeanFactory() {
		return new MyDefaultListableBeanFactory(getInternalParentBeanFactory());
	}
}