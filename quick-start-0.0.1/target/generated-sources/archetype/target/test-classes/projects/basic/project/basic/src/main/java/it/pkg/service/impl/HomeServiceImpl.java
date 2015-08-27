package it.pkg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import it.pkg.dao.HomeMapper;
import it.pkg.pojo.Home;
import it.pkg.pojo.HomeExample;
import it.pkg.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {
	@Autowired
	private HomeMapper homeMapper;
	
	@Override
	public List<Home> selectHomeByExample(HomeExample he) {
		return homeMapper.selectByExample(he, null);
	}

	@Override
	public List<Home> selectHomeByPager(HomeExample he, PageBounds pageBounds) {
		return homeMapper.selectByExample(he, pageBounds);
	}

}
