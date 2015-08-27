package it.pkg.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import it.pkg.pojo.Home;
import it.pkg.pojo.HomeExample;

public interface HomeService {
	List<Home> selectHomeByExample(HomeExample he);
	
	List<Home> selectHomeByPager(HomeExample he, PageBounds pageBounds);
}
