package com.shijie99.basic.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 使用memcache的缓存实用类.
 */
public class MemcacheUtil {
	private final static Logger logger = LoggerFactory
			.getLogger(MemcacheUtil.class.getPackage().getName());

	/**
	 * 创建全局的唯一实例
	 */
	protected MemCachedClient memcacheClient = null;

	/**
	 * 构造方法
	 *
	 */
	public MemcacheUtil(String name, String[] servers, Integer[] weights) {
		if (memcacheClient == null && name != null && !name.equals("")) {
			// 获取socket连接池的实例对象
			SockIOPool pool = SockIOPool.getInstance(name);

			// 设置服务器信息
			pool.setServers(servers);
			pool.setWeights(weights);

			// 设置初始连接数、最小和最大连接数以及最大处理时间
			pool.setInitConn(5);
			pool.setMinConn(5);
			pool.setMaxConn(250);
			pool.setMaxIdle(1000 * 60 * 60 * 6);

			// 设置主线程的睡眠时间
			pool.setMaintSleep(30);

			// 设置TCP的参数，连接超时等
			pool.setNagle(true);
			pool.setSocketTO(3000);
			pool.setSocketConnectTO(2000);
			pool.setAliveCheck(true);
			// 初始化连接池
			pool.initialize();
			
			memcacheClient = new MemCachedClient(name);
		} else {
			logger.error("create memcache client error, as name is null or ''");
		}
	}

	public boolean add(String key, Object value) {
		if (memcacheClient.add(key, value)) {
			return true;
		} else {
			logger.error("add memcache item of key=" + key + " error!");
			return false;
		}
	}

	public boolean add(String key, Object value, Integer hash) {
		if (memcacheClient.set(key, value, hash)) {
			return true;
		} else {
			logger.error("add memcache item of key=" + key + " error!");
			return false;
		}
	}

	public boolean add(String key, Object value, Date expiry) {
		if (memcacheClient.set(key, value, expiry)) {
			return true;
		} else {
			logger.error("add memcache item of key=" + key + " error!");
			return false;
		}
	}

	public boolean addObj(String key, Object value, Date expiry) {
		if (memcacheClient.add(key, value, expiry)) {
			return true;
		} else {
			logger.error("add memcache item of key=" + key + " error!");
			return false;
		}
	}

	public boolean replace(String key, Object value) {
		if(memcacheClient != null) {
			return memcacheClient.replace(key, value);
		} else {
			return false;
		}
	}

	public boolean replace(String key, Object value, Date expiry) {
		return memcacheClient.replace(key, value, expiry);
	}

	public Object get(String key) {
		return memcacheClient.get(key);
	}

	public boolean remove(String key) {
		return memcacheClient.delete(key);
	}
}