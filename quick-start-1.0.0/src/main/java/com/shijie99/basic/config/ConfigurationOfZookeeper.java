package com.shijie99.basic.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.shijie99.basic.util.Constant;

@Component("configZookeeper")
public class ConfigurationOfZookeeper implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationOfZookeeper.class.getPackage().getName());
	
	private String zookeeperURL = "localhost:2181";
	private Integer zookeeperTimeOut = 5000;
	private String zookeeperPath = "default";  
    private ZooKeeper zk;  
    private static Stat stat = null;
    private volatile Map<String, String> serverList;
    
    @Resource(type=Configuration.class)
    Configuration configuration;
    
    public ConfigurationOfZookeeper() { 
    	
    }
	
	private void monitorZookeeper() throws Exception {  
        zk = new ZooKeeper(zookeeperURL, zookeeperTimeOut, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if(event.getType() == EventType.NodeChildrenChanged
					&& (Constant.SLASH + zookeeperPath).equals(event.getPath())) {
					try {
						updateServerList();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
        
        updateServerList();
    }
    
    private void updateServerList() throws Exception {
    	Map<String, String> configMap = new HashMap<String, String>();
    	
    	List<String> subList = zk.getChildren(Constant.SLASH + zookeeperPath, true);
    	for(String subNode : subList) {
    		byte[] data = zk.getData(Constant.SLASH + zookeeperPath + 
    				Constant.SLASH + subNode, false, stat);
    		configMap.put(subNode, new String(data, "UTF-8"));
    	}
    	
    	// 替换server列表  
        serverList = configMap;  
        
        print();
    } 
    
    private void print() {
    	Iterator<Entry<String, String>> iter = serverList.entrySet().iterator();
    	while(iter.hasNext()) {
    		Entry<String, String> entry =  iter.next();
    		logger.info(entry.getKey() + "==>" + entry.getValue());
    	}
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		if(stat == null) {
			zookeeperURL = configuration.getZookeeperURL();
	    	zookeeperTimeOut = configuration.getZookeeperTimeOut();
	    	zookeeperPath = configuration.getZookeeperPath();
	    	
	    	try {
	    		monitorZookeeper();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
