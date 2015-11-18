package com.shijie99.basic.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.shijie99.basic.BaseTestCase;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;

public class ServiceOfHomeTest extends BaseTestCase {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private HomeService homeService;
	
	@Test
	public void getHome() {
		HomeExample he = new HomeExample();
		he.createCriteria().andIdEqualTo(1);
		List<Home> home = homeService.selectHomeByExample(he);
		Assert.assertEquals(home.size() > 0,  true);
	}
}
