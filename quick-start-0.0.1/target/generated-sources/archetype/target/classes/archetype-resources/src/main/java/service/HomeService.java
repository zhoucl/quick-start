#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import ${package}.pojo.Home;
import ${package}.pojo.HomeExample;

public interface HomeService {
	List<Home> selectHomeByExample(HomeExample he);
	
	List<Home> selectHomeByPager(HomeExample he, PageBounds pageBounds);
}
