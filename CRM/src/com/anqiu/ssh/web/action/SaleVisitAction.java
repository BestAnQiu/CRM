package com.anqiu.ssh.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.domain.SaleVisit;
import com.anqiu.ssh.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	//最后拜访时间
	private Date visit_end_time;
	
	
	public Date getVisit_end_time() {
		return visit_end_time;
	}

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	//分页数据
	private Integer currPage=1;
	private Integer pageSize=3;
		
	public void setCurrPage(Integer currPage) {
		if (currPage==null) {
			currPage=1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize==null) {
			pageSize=3;
		}
		this.pageSize = pageSize;
	}

	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	//查询所有加分页
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		//设置条件
		if (saleVisit.getVisit_time()!=null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if (visit_end_time!=null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		System.out.println(pageBean);
		return "findAll";
	}
	//跳转到保存客户页面
	public String saveUI(){
		
		return "saveUI";
	}
	public String save(){
		saleVisitService.save(saleVisit);
		return "save";
	}
}
