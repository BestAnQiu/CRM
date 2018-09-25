package com.anqiu.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.anqiu.ssh.domain.Customer;
import com.anqiu.ssh.domain.PageBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();
	
}
