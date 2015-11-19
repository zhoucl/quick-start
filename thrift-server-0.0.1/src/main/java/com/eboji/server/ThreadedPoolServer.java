package com.eboji.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadedPoolServer implements Server {
	private static final Logger logger = LoggerFactory
			.getLogger(ThreadedPoolServer.class);

	private static final ThreadedPoolServer threadedPoolServer = new ThreadedPoolServer();

	private ThreadedPoolServer() {
	}

	public static final ThreadedPoolServer getInstance() {
		return threadedPoolServer;
	}
	
	@Override
	public TServer createServer(int port, int clientTimeout,
			TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory,
			TMultiplexedProcessor tMultiplexedProcessor) {
		logger.info("Starting the Threaded Pool server...");
		
		TServer server = null;
		try {
			TServerTransport serverSocket = new TServerSocket(port, clientTimeout);
			TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);

			args.protocolFactory(tProtocolFactory);
			args.processor(tMultiplexedProcessor);

			server = new TThreadPoolServer(args);
		} catch (Exception e) {
			logger.error("Threaded Pool server initilized error!\n"
					+ e.getMessage());
		}
		
		return server;
	}

	@Override
	public void stopServer(TServer server) {
		server.stop();

		logger.info("Threaded Pool server stopped success!");
	}

}
