package com.shijie99.basic.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;

public interface HomeService {
	List<Home> selectHomeByExample(HomeExample he);
	
	List<Home> selectHomeByPager(HomeExample he, PageBounds pageBounds);
}
