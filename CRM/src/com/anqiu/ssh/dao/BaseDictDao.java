package com.anqiu.ssh.dao;

import java.util.List;

import com.anqiu.ssh.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code);
	
}
