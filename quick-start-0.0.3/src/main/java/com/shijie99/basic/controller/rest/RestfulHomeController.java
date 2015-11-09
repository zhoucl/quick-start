package com.shijie99.basic.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;
import com.shijie99.basic.service.HomeService;

@RestController
@RequestMapping("/home/rest")
public class RestfulHomeController {
	private static final Logger logger = LoggerFactory.getLogger(RestfulHomeController.class.getPackage().getName());
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/all/{page}", method = RequestMethod.GET)
	@ResponseBody
	public List<Home> all(@PathVariable("page") String page) {
		PageBounds pageBounds = new PageBounds(Integer.parseInt(page), 1, true);
		List<Home> homes = homeService.selectHomeByPager(null, pageBounds);
		
		logger.trace("index trace");
		logger.debug("index debug");
		logger.info("index info");
		logger.warn("index warn");
		logger.error("index error");
		
		return homes;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Home index(@PathVariable("id") String id) {
		HomeExample he = new HomeExample();
		he.createCriteria().andIdEqualTo(Integer.parseInt(id));

		List<Home> homeList = homeService
				.selectHomeByExample(he);
		if (homeList != null && !homeList.isEmpty()) {
			return homeList.get(0);
		} else {
			return null;
		}
	}
}
