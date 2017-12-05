package com.wch.bos.dao.impl.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wch.bos.dao.impl.base.IBaseDao;
import com.wch.bos.utils.PageBean;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	private List<?> findByCriteria;
	
	@Resource
	private void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public BaseDaoImpl() {
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = superClass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "FROM " + entityClass.getSimpleName(); 
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		 Session session = this.getSessionFactory().getCurrentSession();
		 Query query = session.getNamedQuery(queryName);
		 int i = 0;
		 for (Object object : objects) {
			query.setParameter(i++, object);
		}
		 query.executeUpdate();
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSise = pageBean.getPageSize();
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		//
		criteria.setProjection(Projections.rowCount());
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		Long count = countList.get(0);
		
		pageBean.setTotal(count.intValue());
		//
		criteria.setProjection(null);
		int firstResult = (currentPage - 1) * pageSise;
		int maxResults = pageSise;
		List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		
		pageBean.setRows(rows);
	}

}
