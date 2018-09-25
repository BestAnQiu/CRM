package com.anqiu.ssh.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.anqiu.ssh.domain.Customer;
import com.anqiu.ssh.domain.PageBean;
import com.anqiu.ssh.service.CustomerService;
import com.anqiu.ssh.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//分页
	private Integer currPage=1;	
	public void setCurrPage(Integer currPage) {
		if (currPage==null) {
			currPage=1;
		}
		this.currPage = currPage;
	}
	private Integer pageSize=3;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	//文件上传
	private String uploadFileName;//文件名称
	private File upload;		  //上传文件
	private String uploadContentType;//文件类型
			
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//跳转到添加页面
	public String saveUI(){
		System.out.println("save方法");
		return "saveUI";
	}
	
	//添加客户信息操作
	public String save() throws IOException{
		//上传图片
		if (upload != null) {
			//上传文件
			//设置文件上传路径
			String path="F:/upload";
			//一个目录下存放的相同文件名：随机文件名
			String uuidfileName = UploadUtils.getUuidfileName(uploadFileName);
			//一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidfileName);
			String url=path+realPath;
			//创建目录
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidfileName);
			FileUtils.copyFile(upload, dictFile);
			//保存到数据库
			customer.setCust_image(url+"/"+uuidfileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}
	
	public String findAll(){
		DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Customer.class);
		//		在detachedCriteria中添加分页条件
		if (customer.getCust_name()!=null && !"".equals(customer.getCust_name())) {
			detachedCriteria.add(Restrictions.like("cust_name",
					"%"+customer.getCust_name()+"%"));
		}
		if (customer.getBaseDictSource()!=null) {
			if (customer.getBaseDictSource().getDict_id()!=null &&
					!"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id",
						customer.getBaseDictSource().getDict_id()));
			}
		}
		if (customer.getBaseDictLevel()!=null) {
			if (customer.getBaseDictLevel().getDict_id()!=null &&
					!"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id",
						customer.getBaseDictLevel().getDict_id()));
			}
		}
		if (customer.getBaseDictIndustry()!=null) {
			if (customer.getBaseDictIndustry().getDict_id()!=null &&
					!"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id",
						customer.getBaseDictIndustry().getDict_id()));
			}
		}
		PageBean<Customer> pageBean = customerService.findAll(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	//销售拜访ajax查询客户全部信息
	public String findAllCustomer() throws IOException{
		List<Customer> list = customerService.findAll();
		System.out.println("customer数据"+list.toArray());
		//json转换
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]
				{"linkMans","baseDictSource","baseDictIndustry","baseDictLevel"});
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
		return NONE;
	}
	public String delete(){
		customer = customerService.findById(customer.getCust_id());
		if (customer.getCust_image()!=null) {
			File file=new File(customer.getCust_image());
			if (file.exists()) {
				file.delete();
			}
		}
		customerService.delete(customer);
		return "delete";
	}
	//	修改用户信息
	public String edit(){
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	//	修改客户信息
	public String update() throws IOException{
		//文件是否已经选择吗，如果选择了，就删除原有文件，上传新的文件，如果没有选，使用原有即可
		if (upload!=null) {
			//已经选择了，删除原有文件
			String cust_image = customer.getCust_image();
			if (cust_image!=null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				file.delete();
			}
			//上传文件
			//设置文件上传路径
			String path="F:/upload";
			//一个目录下存放的相同文件名：随机文件名
			String uuidfileName = UploadUtils.getUuidfileName(uploadFileName);
			//一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidfileName);
			String url=path+realPath;
			//创建目录
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidfileName);
			FileUtils.copyFile(upload, dictFile);
			//保存到数据库
			customer.setCust_image(url+"/"+uuidfileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
}
