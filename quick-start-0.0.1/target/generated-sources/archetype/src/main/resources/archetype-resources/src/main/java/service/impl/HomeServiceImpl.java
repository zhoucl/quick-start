#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import ${package}.dao.HomeMapper;
import ${package}.pojo.Home;
import ${package}.pojo.HomeExample;
import ${package}.service.HomeService;

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
