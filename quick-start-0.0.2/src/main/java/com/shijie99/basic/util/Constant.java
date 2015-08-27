package com.shijie99.basic.util;

public class Constant {
	/**
	 * AOP连接点信息常量
	 */
	public final static String POINT_CUT_SWITCHDB_EL = "execution(* com.shijie99.basic.service.*.*(..))";

	/**
	 * 
	 */
	public final static String DB_LOCAL_KEY = "localDataSource";	//需要与spring-mybatis.xml中的数据源ID对应
	public final static String DB_REMOTE_KEY = "remoteDataSource";	//同上
}
