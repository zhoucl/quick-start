package com.shijie99.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shijie99.basic.config.Configuration;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class.getPackage().getName());
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private Configuration configComp;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Home home = new Home();
		home.setId(10);
		home.setHomelink("http://www.baidu.com");
		home.setHomedesc("desc");
		home.setRemark("remark");
		
		String[] str = new String[1024000];
		configComp.getMemcacheUtil().add(request.getSession().getId(), JSONObject.fromObject(home).toString());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("success ===> " + str.length);
		return "index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response) {
		Object obj = configComp.getMemcacheUtil().get(request.getSession().getId());
		if(obj != null) {
			String session = configComp.getMemcacheUtil().get(request.getSession().getId()).toString();
			Home home = (Home)JSONObject.toBean(JSONObject.fromObject(session), Home.class);
			System.out.println(home.getHomelink());
		}
		return "biz/index";
	}
}
