package com.eboji.bootstrap;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eboji.annotation.ThriftHandler;
import com.eboji.annotation.ThriftProcessor;
import com.eboji.bootstrap.config.SpringConfiguration;
import com.eboji.server.ThreadedPoolServer;
import com.eboji.util.ClassUtil;
import com.eboji.util.Constant;
import com.eboji.vo.ServerConfigVO;

public class Daemon {
	private static final Logger logger = LoggerFactory.getLogger(Daemon.class);
	
	private ServerConfigVO serverConfigVO = null;
	
	private TMultiplexedProcessor tMultiplexedProcessor = null;
	
	private static final Daemon daemon = new Daemon();

	private AnnotationConfigApplicationContext context = null;

	private Daemon() {
	}

	private void initContext(String[] args) {
		context = null;
		context = new AnnotationConfigApplicationContext();
		context.register(SpringConfiguration.class);

		int port = Integer.parseInt(System.getProperty(
				"java.util.config.server.port"));
		int clientTimeout = Integer.parseInt(System.getProperty(
				"java.util.config.server.timeout"));
		String thriftServerType = System.getProperty(
				"java.util.config.server.type", "TSimpleServer");
		
		initTMultiplexedProcessor();
		
		initServerConfig(port, clientTimeout);
	}

	private void initServerConfig(int port, int clientTimeout) {
		if(serverConfigVO == null) {
			serverConfigVO = new ServerConfigVO();
			serverConfigVO.setPort(port);
			serverConfigVO.setClientTimeout(clientTimeout);
			serverConfigVO.settMultiplexedProcessor(tMultiplexedProcessor);
			serverConfigVO.settTransportFactory(new TFramedTransport.Factory(1024));
			serverConfigVO.settProtocolFactory(new TCompactProtocol.Factory(1024));
		}
	}
	
	private void initTMultiplexedProcessor() {
		tMultiplexedProcessor = null;
		tMultiplexedProcessor = new TMultiplexedProcessor();
		
		List<Class<?>> processorList = new ArrayList<Class<?>>();
		List<Class<?>> handlerList = new ArrayList<Class<?>>();

		findProcessorAndHandler(processorList, handlerList);
		
		registerProcessor(processorList, handlerList);
	}
	
	private void findProcessorAndHandler(List<Class<?>> processorList, 
			List<Class<?>> handlerList) {
		List<Class<?>> clazzList = ClassUtil.scanPackage(Constant.THRIFT_SERVICE_PKG);
		
		for(Class<?> clazz : clazzList) {
			if(clazz.getAnnotation(ThriftProcessor.class) != null) {
				processorList.add(clazz);
				logger.info("loading " + clazz.getCanonicalName());
			}
			if(clazz.getAnnotation(ThriftHandler.class) != null) {
				handlerList.add(clazz);
				logger.info("loading " + clazz.getCanonicalName());
			}
		}
	}
	
	private void registerProcessor(List<Class<?>> processorList, 
			List<Class<?>> handlerList) {
		try {
			for(Class<?> clazz : processorList) {
				Class<?> processor = Class.forName(clazz.getCanonicalName() + "$Processor");
				Class<?> iface = Class.forName(clazz.getCanonicalName() + "$Iface");
				Constructor<?> constructor = processor.getConstructor(iface);
				
				for(Class<?> clazzHandler : handlerList) {
					if(clazz.getAnnotation(ThriftProcessor.class).value().equals(clazzHandler.getSimpleName())) {
						TProcessor tProcessor = (TProcessor)constructor.newInstance(clazzHandler.newInstance());
						String serviceName = clazz.getAnnotation(ThriftProcessor.class).value();
						tMultiplexedProcessor.registerProcessor(serviceName, tProcessor);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("thrift service register fail!");
			System.exit(0);
		}
	}
	
	public static Daemon getInstance() {
		return daemon;
	}

	public void load(String[] args) {
		initContext(args);
	}

	public void start() {
		ServerConfigVO scVO = new ServerConfigVO();
		scVO.setPort(9090);
		scVO.setClientTimeout(30000);
		scVO.settTransportFactory(null);
		scVO.settProtocolFactory(null);
		scVO.settMultiplexedProcessor(null);

		TServer server = ThreadedPoolServer.getInstance().createServer(
				scVO.getPort(), scVO.getClientTimeout(),
				scVO.gettProtocolFactory(), scVO.gettTransportFactory(),
				scVO.gettMultiplexedProcessor());

		TServerStartThread st = new TServerStartThread(server);
		st.start();
	}

	public void restart() {

	}

	public void stop() {

	}
}
