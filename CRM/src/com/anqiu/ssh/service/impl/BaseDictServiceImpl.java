package com.anqiu.ssh.service.impl;

import java.util.List;

import com.anqiu.ssh.dao.BaseDictDao;
import com.anqiu.ssh.domain.BaseDict;
import com.anqiu.ssh.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {
	
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
