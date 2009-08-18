package org.jtester.unitils.spring;

import org.jtester.unitils.jmock.BeanProxy;
import org.jtester.unitils.jmock.MockBeanRegister;
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
			// Object bean = MockBeanRegister.getBeanByName(name);
			// return bean == null ? super.getBean(name, requiredType, args) :
			// bean;
			Class<?> typeMocked = MockBeanRegister.hasRegisted(name, requiredType);
			if (typeMocked != null) {
				return BeanProxy.proxy(name, typeMocked);
			} else {
				return super.getBean(name, requiredType, args);
			}
		}

		@Override
		public boolean containsBean(String name) {
			if (MockBeanRegister.hasRegisted(name)) {
				return true;
			} else {
				return super.containsBean(name);
			}
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