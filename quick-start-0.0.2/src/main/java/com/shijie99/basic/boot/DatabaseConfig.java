package com.shijie99.basic.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:spring-mybatis.xml"})

/**
 * 由于使用@MapperScan标签，无法通过指定sqlSessionFactoryBeanName指定特定的sqlSessionFactory
 * 故对于数据源和sqlSessionfactory，事务，mybatis自动扫描等暂时使用XML方式进行配置
 * @author zhoucl
 *
 */
public class DatabaseConfig {
}
