package com.shijie99.basic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shijie99.basic.config.MultipleDataSource;
import com.shijie99.basic.service.FlightBookService;
import com.shijie99.basic.service.HomeService;
import com.shijie99.basic.util.Constant;

@Component
@Aspect
public class MultipleDataSourceAspect {
	private static final Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspect.class);
	
	@Pointcut(value=Constant.POINT_CUT_SWITCHDB_EL)
	private void switchDB() {
		
	}
	
	@Around("switchDB()")
	public Object doAround(ProceedingJoinPoint jp) throws Throwable{
		logger.info("switch datasource start...");
		int type = 0;
		
		if(isLocalService(jp.getTarget())) {
			MultipleDataSource.setDataSourceKey(Constant.DB_LOCAL_KEY);
			type = 0;
		} else if(isRemoteService(jp.getTarget())) {
			MultipleDataSource.setDataSourceKey(Constant.DB_REMOTE_KEY);
			type = 1;
		}
		
		logger.info("datasource has been switched to [" + 
				(type == 0 ? Constant.DB_LOCAL_KEY : Constant.DB_REMOTE_KEY));
		return jp.proceed();
	}
	
	/**
	 * 判断是否是本地service
	 * @param object
	 * @return
	 */
	private boolean isLocalService(Object object) {
		//TODO
		//在此增加更多的service
		if(object instanceof HomeService) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断是否是远程service
	 * @param object
	 * @return
	 */
	private boolean isRemoteService(Object object) {
		//TODO
		//可在此处增加更多的service
		if(object instanceof FlightBookService) {
			return true;
		}
		
		return false;
	}
}
