package com.eboji.bootstrap;

import org.apache.thrift.server.TServer;

public class TServerStartThread extends Thread {
	private TServer tserver = null;
	
	public TServerStartThread(TServer tserver) {
		this.tserver = tserver;
	}
	
	@Override
	public void run() {
		if(tserver != null) {
			tserver.serve();
		}
	}
}
