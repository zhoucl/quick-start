package com.shijie99.basic.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;
import com.shijie99.basic.service.FlightBookService;
import com.shijie99.basic.service.HomeService;

@RestController
@RequestMapping("/home/rest")
public class HomeControllerRestful {
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private FlightBookService flightBookService;
	
	@RequestMapping(value = "/all/{page}", method = RequestMethod.GET)
	@ResponseBody
	public List<Home> all(@PathVariable("page") String page) {
		PageBounds pageBounds = new PageBounds(Integer.parseInt(page), 1, true);
		List<Home> homes = homeService.selectHomeByPager(null, pageBounds);
		
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
	
	@RequestMapping(value = "/db/{lr}", method = RequestMethod.GET)
	public String chooseDB(@PathVariable("lr") String lr) {
		if(lr.equals("1")) {
			PageBounds pageBounds = new PageBounds(1, 1, true);
			List<Home> homes = homeService.selectHomeByPager(null, pageBounds);
			
			return "count: " + (homes != null ? homes.size() :0);
 		} else if(lr.equals("0")) {
			int count = flightBookService.countByExample(null);
			
			return "count: " + count;
		}
		
		return null;
	}
}
