package com.anqiu.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.anqiu.ssh.dao.CustomerDao;
import com.anqiu.ssh.dao.UserDao;
import com.anqiu.ssh.domain.Customer;
import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);	
	}

	@Override
	public PageBean<Customer> findAll(DetachedCriteria detachedCriteria,Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		System.out.println("pageSize"+pageSize);
		//封装总条数
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//封装每页的数据
		System.out.println("pageSize1"+"-"+pageSize);
		System.out.println("currPage1"+"-"+currPage);
		Integer begin=(currPage-1) * pageSize;
		System.out.println("pageSize2"+pageSize);
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		//封装总页数
		Double tc=totalCount.doubleValue();
		Integer totalPage = (int) Math.ceil(tc/pageSize);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {
		Customer customer = customerDao.findById(cust_id);
		return customer;
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
		
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> list = customerDao.findAll();
		return list;
	}
}
