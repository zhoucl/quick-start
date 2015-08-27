package it.pkg.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import it.pkg.pojo.Home;
import it.pkg.pojo.HomeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HomeMapper {
    int countByExample(HomeExample example);

    int deleteByExample(HomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    int insertSelective(Home record);

    List<Home> selectByExample(HomeExample example, PageBounds pageBounds);

    Home selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Home record, @Param("example") HomeExample example);

    int updateByExample(@Param("record") Home record, @Param("example") HomeExample example);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
}