package com.anqiu.ssh.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.anqiu.ssh.domain.BaseDict;
import com.anqiu.ssh.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>{
	
	private BaseDict basedict = new BaseDict();
	@Override
	public BaseDict getModel() {
		// TODO Auto-generated method stub
		return basedict;
	}
	
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	public String findByTypeCode() throws IOException{
		System.out.println("方法执行了");
		List<BaseDict> list = baseDictService.findByTypeCode(basedict.getDict_type_code());
		//json转换
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		System.out.println(jsonArray);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray);
		return NONE;
	}
}
