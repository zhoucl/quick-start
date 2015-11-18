package com.shijie99.basic.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类入口
 * @author zhoucl
 */
@Configuration
@Import({
	SpringConfig.class, 		//spring 初始化bean相关的配置
	DatabaseConfig.class		//数据库加载相关的配置(mybatis等)
})
public class RootConfig {

}
