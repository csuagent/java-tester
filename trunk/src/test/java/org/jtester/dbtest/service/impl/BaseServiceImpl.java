package org.jtester.dbtest.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jtester.dbtest.bean.BaseBean;
import org.jtester.dbtest.service.BaseService;

public class BaseServiceImpl<T extends BaseBean> implements BaseService<T> {
	protected Class<T> claz;

	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		this.claz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public <E extends BaseBean> E getBeanById(Class<E> clazz, int id) {
		String hql = "from " + clazz.getName() + " as bean where bean.id=:id and bean.deleted=false";
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		q.setInteger("id", id);
		E bean = (E) q.uniqueResult();
		return bean;
	}

	public T getBeanById(int id) {
		return this.getBeanById(claz, id);
	}

	public <E extends BaseBean> E save(E bean) {
		if (bean.getId() == 0) {
			Serializable pKey = this.currSession().save(bean);
			return (E) this.currSession().get(bean.getClass(), pKey);
		} else {
			this.currSession().saveOrUpdate(bean);
			return bean;
		}
	}

	public Session currSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
