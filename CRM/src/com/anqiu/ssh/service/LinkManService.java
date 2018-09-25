package com.anqiu.ssh.service;

import org.hibernate.criterion.DetachedCriteria;

import com.anqiu.ssh.domain.LinkMan;
import com.anqiu.ssh.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
