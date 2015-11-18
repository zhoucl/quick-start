package com.shijie99.basic.service;

import java.util.List;

import com.shijie99.basic.pojo.CfgServers;

public interface ConfigService {
	public List<CfgServers> listMemcacheServers(int type);
}
