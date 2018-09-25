package com.anqiu.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.anqiu.ssh.dao.LinkManDao;
import com.anqiu.ssh.domain.LinkMan;
import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.service.LinkManService;

@Transactional
public class LinkManServiceImpl implements LinkManService{

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean=new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		Double tc=totalCount.doubleValue();
		Integer totalPage=(int) Math.ceil(tc/pageSize);
		pageBean.setTotalPage(totalPage);
		
		Integer index = (currPage-1)*pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,index,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
		
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		LinkMan linkMan = linkManDao.findById(lkm_id);
		return linkMan;
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
		
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
		
	}

	
}
