package com.anqiu.ssh.service;

import org.hibernate.criterion.DetachedCriteria;

import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);
	
}
