#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
