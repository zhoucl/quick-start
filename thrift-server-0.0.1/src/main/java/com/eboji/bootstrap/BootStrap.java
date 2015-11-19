package com.eboji.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 项目启动的入口类
 * @author zhoucl
 */
public class BootStrap {
	private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);
	
	private static final Daemon daemon = Daemon.getInstance();
	
	static {
		ShutdownHook.doShutdownHook();
	}

	public static void main(String[] args) {
		try {
			String command = "start";
			if(args.length > 0) {
				command = args[args.length - 1];
			}
			
			if(command.equals("start")) {
				daemon.load(args);
				daemon.start();
			} else if(command.equals("stop")) {
				daemon.stop();
			} else if(command.equals("restart")) {
				daemon.restart();
			} else {
				logger.warn("Bootstrap: command \"" + command + "\" does not exist.");
			}
		} catch (Exception e) {
			logger.error("server start fail!");
			System.exit(1);
		}
	}
}
