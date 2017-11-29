package com.wch.bos.dao.impl.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wch.bos.dao.impl.base.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	
	@Resource
	private void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	
	public BaseDaoImpl(Class<T> entityClass) {
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = superClass.getActualTypeArguments();
		
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
