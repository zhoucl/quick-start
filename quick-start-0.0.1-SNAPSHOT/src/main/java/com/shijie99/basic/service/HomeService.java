package com.shijie99.basic.service;

import java.util.List;

import com.shijie99.basic.pojo.Home;
import com.shijie99.basic.pojo.HomeExample;

public interface HomeService {
	List<Home> selectHomeByExample(HomeExample he);
}
