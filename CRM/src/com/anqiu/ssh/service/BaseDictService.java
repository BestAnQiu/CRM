package com.anqiu.ssh.service;

import java.util.List;

import com.anqiu.ssh.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
