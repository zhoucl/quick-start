package com.shijie99.basic.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Bean主配置类
 * @author zhoucl
 */
@Configuration
@ComponentScan(value = { "org.shijie99.basic" },
	excludeFilters = 
	{ 
		@ComponentScan.Filter(type = FilterType.ANNOTATION, 
			value = {
				Controller.class,
				RestController.class
			}
		)
	}
)
public class SpringConfig {

}
