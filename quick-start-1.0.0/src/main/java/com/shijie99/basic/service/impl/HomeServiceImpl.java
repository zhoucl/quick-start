package com.shijie99.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shijie99.basic.dao.HomeMapper;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;
import com.shijie99.basic.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {
	@Autowired
	private HomeMapper homeMapper;
	
	@Override
	public List<Home> selectHomeByExample(HomeExample he) {
		return homeMapper.selectByExample(he, new PageBounds());
	}

	@Override
	public List<Home> selectHomeByPager(HomeExample he, PageBounds pageBounds) {
		return homeMapper.selectByExample(he, pageBounds);
	}

}
