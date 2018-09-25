package com.anqiu.ssh.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.anqiu.ssh.domain.LinkMan;

public interface LinkManDao {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
