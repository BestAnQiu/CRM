package com.anqiu.ssh.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.anqiu.ssh.domain.Customer;
import com.anqiu.ssh.domain.LinkMan;
import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.service.CustomerService;
import com.anqiu.ssh.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	private LinkMan linkMan=new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	//注入客户service
	private CustomerService customerService;	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

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

	//联系人查询方法
	public String findAll(){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		if (linkMan.getLkm_name() != null) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if (linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		PageBean<LinkMan> pageBean = linkManService.findAll
				(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转到保存联系人页面
	public String saveUI(){
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUISuccess";
	}
	//保存联系人
	public String save(){
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	//跳转到修改页面
	public String edit(){
		List<Customer> list = customerService.findAll();
		linkMan = linkManService.findById(linkMan.getLkm_id());
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	//修改
	public String update(){
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	//删除
	public String delete(){
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}
