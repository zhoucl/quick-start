package com.eboji.bootstrap;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TZlibTransport;

import com.eboji.thrift.service.HelloWorldService;
import com.eboji.thrift.service.HelloWorldService.Processor;
import com.eboji.thrift.service.impl.HelloWorldServiceImpl;

/**
 * 项目启动的入口类
 * 
 * @author zhoucl
 */
public class BootServer {
	private static HelloWorldServiceImpl serviceImpl;
	private static HelloWorldService.Processor<HelloWorldServiceImpl> processor;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		try {
			serviceImpl = new HelloWorldServiceImpl();
			processor = new Processor<HelloWorldServiceImpl>(serviceImpl);
			
//			BootServer bs = new BootServer();
//			bs.simpleServer();
//			bs.noBlockingServer();
			
			
			final THsHaServer server_ = new THsHaServer(new THsHaServer.Args(new TNonblockingServerSocket(
					new TNonblockingServerSocket.NonblockingAbstractServerSocketArgs().port(9090))).
					processor(new HelloWorldService.Processor(new HelloWorldServiceImpl())));
			Thread serverThread_ = new Thread(new Runnable() {
				public void run() {
					server_.serve();
				}
			});
			serverThread_.start();
			
			
//			bs.threadPoolServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void simpleServer() {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer.Args args = new TServer.Args(serverTransport);
			
			TProtocolFactory tprotocolFactory = new TCompactProtocol.Factory(1024);
			args.protocolFactory(tprotocolFactory);
			
			args.transportFactory(new TZlibTransport.Factory());
			
			TMultiplexedProcessor tMultiplexedProcessor = new TMultiplexedProcessor();
			tMultiplexedProcessor.registerProcessor("HelloWorldService", processor);
			args.processor(tMultiplexedProcessor);

			TServer server = new TSimpleServer(args);
			
			System.out.println("Starting the simple server...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	
	public void noBlockingServer(){
		try {
			TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(9090);
			TNonblockingServer.Args args = new TNonblockingServer.Args(serverSocket);
			
//			TProtocolFactory tprotocolFactory = new TCompactProtocol.Factory(1024);
//			args.protocolFactory(tprotocolFactory);
//			
//			TTransportFactory ttransportFactory = new TFramedTransport.Factory(1024);
//			args.transportFactory(ttransportFactory);
			
			TMultiplexedProcessor tMultiplexedProcessor = new TMultiplexedProcessor();
			tMultiplexedProcessor.registerProcessor("HelloWorldService", processor);
			args.processor(tMultiplexedProcessor);

			TServer server = new TNonblockingServer(args);

			System.out.println("Starting the non blocking server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hsHaServer() {
		try {
			TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(9090);
			THsHaServer.Args args = new THsHaServer.Args(serverSocket);

			TProtocolFactory tprotocolFactory = new TCompactProtocol.Factory(1024);
			args.protocolFactory(tprotocolFactory);
			
			TTransportFactory ttransportFactory = new TFramedTransport.Factory(1024);
			args.transportFactory(ttransportFactory);
			
			TMultiplexedProcessor tMultiplexedProcessor = new TMultiplexedProcessor();
			tMultiplexedProcessor.registerProcessor("HelloWorldService", processor);
			args.processor(tMultiplexedProcessor);

			TServer server = new THsHaServer(args);

			System.out.println("Starting the non blocking server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void threadSelectorServer() {
		try {
			TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(9090);
			TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverSocket);

			TProtocolFactory tprotocolFactory = new TCompactProtocol.Factory(1024);
			args.protocolFactory(tprotocolFactory);
			
			TTransportFactory ttransportFactory = new TFramedTransport.Factory(1024);
			args.transportFactory(ttransportFactory);
			
			TMultiplexedProcessor tMultiplexedProcessor = new TMultiplexedProcessor();
			tMultiplexedProcessor.registerProcessor("HelloWorldService", processor);
			args.processor(tMultiplexedProcessor);

			TServer server = new TThreadedSelectorServer(args);

			System.out.println("Starting the thread pool server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void threadPoolServer() {
		try {
			TServerTransport serverSocket = new TServerSocket(9090);
			TMultiplexedProcessor tMultiplexedProcessor = new TMultiplexedProcessor();

			tMultiplexedProcessor.registerProcessor("HelloWorldService", processor);

			TProtocolFactory tprotocolFactory = new TCompactProtocol.Factory(1024);
			TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);

			args.protocolFactory(tprotocolFactory);
			args.processor(tMultiplexedProcessor);

			TServer server = new TThreadPoolServer(args);

			System.out.println("Starting the thread pool server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
