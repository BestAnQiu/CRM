package com.anqiu.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.anqiu.ssh.dao.LinkManDao;
import com.anqiu.ssh.domain.LinkMan;

import sun.awt.image.ImageWatched.Link;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer index, Integer pageSize) {
		//清理离线条件数据
		detachedCriteria.setProjection(null);
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
		return list;
	}

	@Override
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);		
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		LinkMan linkMan = this.getHibernateTemplate().get(LinkMan.class, lkm_id);
		return linkMan;
	}

	@Override
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);		
	}

	@Override
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);		
	}
}
