package com.anqiu.ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.anqiu.ssh.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class clazz;
	public BaseDaoImpl() {
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		Type[] types = pType.getActualTypeArguments();
		this.clazz = (Class) types[0];
	}
	
	@Override
	//保存
	public void save(T t) {
		this.getHibernateTemplate().save(t);	
	}

	@Override
	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@Override
	//更新
	public void update(T t) {
		this.getHibernateTemplate().update(t);		
	}

	@Override
	//根据id查找
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
		
	}
	@Override
	//查找全部
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find(" from "+clazz.getSimpleName());
	}
	@Override
	//分页查找总条数
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}
	@Override
	//查找每页的条数信息
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}
}
