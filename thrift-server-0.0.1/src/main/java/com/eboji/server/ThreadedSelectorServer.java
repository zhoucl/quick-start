package com.eboji.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadedSelectorServer implements Server {
	private static final Logger logger = LoggerFactory
			.getLogger(ThreadedSelectorServer.class);

	private static final ThreadedSelectorServer threadedSelectorServer = new ThreadedSelectorServer();

	private ThreadedSelectorServer() {
	}

	public static final ThreadedSelectorServer getInstance() {
		return threadedSelectorServer;
	}

	@Override
	public TServer createServer(int port, int clientTimeout,
			TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory,
			TMultiplexedProcessor tMultiplexedProcessor) {
		logger.info("Starting the Threaded Selector server...");

		TServer server = null;
		try {
			TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(
					port, clientTimeout);
			TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(
					serverSocket);

			args.protocolFactory(tProtocolFactory);
			args.transportFactory(tTransportFactory);
			args.processor(tMultiplexedProcessor);

			server = new TThreadedSelectorServer(args);
		} catch (Exception e) {
			logger.error("Threaded Selector server initilized error!\n"
					+ e.getMessage());
		}

		return server;
	}

	@Override
	public void stopServer(TServer server) {
		server.stop();

		logger.info("Threaded Selector server stopped success!");
	}

}
