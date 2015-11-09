package com.shijie99.basic.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shijie99.basic.pojo.CfgServers;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;
import com.shijie99.basic.service.ConfigService;
import com.shijie99.basic.service.HomeService;
import com.shijie99.basic.util.Constant;
import com.shijie99.basic.util.MemcacheUtil;

@Component("configComp")
public class Configuration implements InitializingBean {
	private final static Logger logger = LoggerFactory.getLogger(Configuration.class.getPackage().getName());

	private MemcacheUtil memcacheUtil = null;
	
	private String zookeeperURL = null;
	
	private Integer zookeeperTimeOut = null;
	
	private String zookeeperPath = null;
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private ConfigService configService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//初始化bean后供调用的方法
		HomeExample he = new HomeExample();
		he.createCriteria().andIdEqualTo(1);
		List<Home> homeList = homeService.selectHomeByExample(he);
		logger.error(homeList.get(0).getHomedesc());
		
		String memcacheName = "default-memcache";
		String[] servers = null;
		Integer[] weights = null;
		
		List<CfgServers> cfgServers = configService.listMemcacheServers(Constant.MEMCACHE_TYPE);
		
		if(cfgServers != null && cfgServers.size() > 0) {
			servers = new String[cfgServers.size()];
			weights = new Integer[cfgServers.size()];
			memcacheName = cfgServers.get(0).getServername();
			for(int i = 0; i < cfgServers.size(); i++) {
				CfgServers cfgServer = cfgServers.get(i);
				servers[i] = cfgServer.getServer() + ":" + cfgServer.getPort();
				weights[i] = cfgServer.getWeight();
			}
			
			memcacheUtil = new MemcacheUtil(memcacheName, servers, weights);
		}
		
		cfgServers = configService.listMemcacheServers(Constant.ZOOKEEPER_TYPE);
		if(cfgServers != null && cfgServers.size() > 0) {
			zookeeperURL = cfgServers.get(0).getServer() + ":" + cfgServers.get(0).getPort();
			zookeeperTimeOut = cfgServers.get(0).getWeight();
			zookeeperPath = cfgServers.get(0).getServername();
		}
	}

	public MemcacheUtil getMemcacheUtil() {
		return memcacheUtil;
	}

	public String getZookeeperURL() {
		return zookeeperURL;
	}

	public Integer getZookeeperTimeOut() {
		return zookeeperTimeOut;
	}

	public String getZookeeperPath() {
		return zookeeperPath;
	}
}
