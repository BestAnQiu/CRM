package com.anqiu.ssh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.anqiu.ssh.dao.SaleVisitDao;
import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.domain.SaleVisit;
import com.anqiu.ssh.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		//总条数
		Integer totalCount=saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//总页数
		Double tc=totalCount.doubleValue();
		Integer totalPage=(int) Math.ceil(tc/pageSize);
		pageBean.setTotalPage(totalPage);
		//每页的数据
		Integer begin=(currPage-1)*pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
		
	}
	
	
}
