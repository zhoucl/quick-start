package com.shijie99.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shijie99.basic.dao.CfgServersMapper;
import com.shijie99.basic.pojo.CfgServers;
import com.shijie99.basic.pojo.CfgServersExample;
import com.shijie99.basic.service.ConfigService;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private CfgServersMapper cfgServersMapper;
	
	@Override
	public List<CfgServers> listMemcacheServers(int type) {
		CfgServersExample cse = new CfgServersExample();
		cse.createCriteria().andTypeEqualTo(type);
		return cfgServersMapper.selectByExample(cse);
	}

}
