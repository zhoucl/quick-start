package com.eboji;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportFactory;

import com.eboji.thrift.service.HelloWorldService;
import com.eboji.thrift.service.HelloWorldService.Iface;
import com.eboji.thrift.service.HelloWorldService.Processor;
import com.eboji.thrift.service.impl.HelloWorldServiceImpl;

public class Server {
    public static void main( String[] args ) {
    	Server server = new Server();
    	
//    	server.TNonblocking();
    	
    	server.TThreadSelector();
    	
//    	server.TThreadPool();
    	
//    	server.TSimple();
    }
    
    private void TNonblocking() {
    	try {
    		HelloWorldServiceImpl serviceImpl = new HelloWorldServiceImpl();
    		HelloWorldService.Processor<Iface> processor = new Processor<Iface>(serviceImpl);
    		
	    	TServer serverEngine = null;
	    	TTransportFactory tTransportFactory = new TFastFramedTransport.Factory();
	    	TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
	    	
	    	TNonblockingServerSocket tNonblockingServerSocket =
	    			new TNonblockingServerSocket(9090,20000);
	    	
	    	TNonblockingServer.Args argss =
	    			new TNonblockingServer.Args(tNonblockingServerSocket);
	    	argss.processor(processor);
	    	argss.transportFactory(tTransportFactory);
	    	argss.protocolFactory(tProtocolFactory);
	    	
	    	serverEngine = new TNonblockingServer(argss);
	    	
	    	System.out.println("server started!");
	    	serverEngine.serve();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void TThreadSelector() {
    	try {
    		HelloWorldServiceImpl serviceImpl = new HelloWorldServiceImpl();
    		HelloWorldService.Processor<Iface> processor = new Processor<Iface>(serviceImpl);
    		
	    	TServer serverEngine = null;
	    	TTransportFactory tTransportFactory = new TFastFramedTransport.Factory();
	    	TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
	    	
	    	TNonblockingServerSocket tNonblockingServerSocket =
	    			new TNonblockingServerSocket(9090,20000);
	    	
	    	TThreadedSelectorServer.Args argss =
	    			new TThreadedSelectorServer.Args(tNonblockingServerSocket);
	    	argss.processor(processor);
	    	argss.transportFactory(tTransportFactory);
	    	argss.protocolFactory(tProtocolFactory);
	    	argss.selectorThreads(10);
	    	
	    	ExecutorService executorService = Executors.newFixedThreadPool(5);
	    	argss.executorService(executorService);
	    	
	    	serverEngine = new TThreadedSelectorServer(argss);
	    	
	    	System.out.println("server started!");
	    	serverEngine.serve();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void TThreadPool() {
    	try {
    		HelloWorldServiceImpl serviceImpl = new HelloWorldServiceImpl();
    		HelloWorldService.Processor<Iface> processor = new Processor<Iface>(serviceImpl);
    		
	    	TServer serverEngine = null;
	    	TTransportFactory tTransportFactory = new TTransportFactory();
	    	TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
	    	
	    	TServerSocket tServerSocket =
	    			new TServerSocket(9090,20000);
	    	
	    	TThreadPoolServer.Args argss =
	    			new TThreadPoolServer.Args(tServerSocket);
	    	argss.processor(processor);
	    	argss.transportFactory(tTransportFactory);
	    	argss.protocolFactory(tProtocolFactory);
	    	
	    	ExecutorService executorService = Executors.newFixedThreadPool(5);
	    	argss.executorService(executorService);
	    	argss.minWorkerThreads(2);
	    	argss.maxWorkerThreads(10);
	    	
	    	serverEngine = new TThreadPoolServer(argss);
	    	
	    	System.out.println("server started!");
	    	serverEngine.serve();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void TSimple() {
    	try {
    		HelloWorldServiceImpl serviceImpl = new HelloWorldServiceImpl();
    		HelloWorldService.Processor<Iface> processor = new Processor<Iface>(serviceImpl);
    		
	    	TServer serverEngine = null;
	    	TTransportFactory tTransportFactory = new TTransportFactory();
	    	TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
	    	
	    	TServerSocket tServerSocket =
	    			new TServerSocket(9090,20000);
	    	
	    	TServer.Args argss =
	    			new TServer.Args(tServerSocket);
	    	argss.processor(processor);
	    	argss.transportFactory(tTransportFactory);
	    	argss.protocolFactory(tProtocolFactory);
	    	
	    	serverEngine = new TSimpleServer(argss);
	    	
	    	System.out.println("server started!");
	    	serverEngine.serve();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
