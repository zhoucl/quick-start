package com.eboji;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TZlibTransport;

import com.eboji.thrift.service.HelloWorldService;
import com.eboji.thrift.service.HelloWorldService.AsyncClient.add_call;

public class Client {
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		
//		client.simple();
		client.asyncClient();
//		client.nonBlocking();
//		client.threadPool();
	}
	
	public void simple() throws Exception {
		TSocket socket = new TSocket("localhost", 9090);
		TZlibTransport transport = new TZlibTransport(socket);
		TProtocol protocol = new TCompactProtocol(transport);
		TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol, "HelloWorldService");
		HelloWorldService.Client client = new HelloWorldService.Client(tMultiplexedProtocol);
		transport.open();
		System.out.println(client.add(10, 20));
		transport.close();
	}
	
	public void asyncClient() throws Exception {
		TAsyncClientManager clientManager = new TAsyncClientManager();  
        TNonblockingTransport transport = new TNonblockingSocket("localhost", 9090);  
        TProtocolFactory protocol = new TBinaryProtocol.Factory();  
        HelloWorldService.AsyncClient asyncClient = new HelloWorldService.AsyncClient(protocol, clientManager, transport);  
        System.out.println("Client calls .....");  
        
        MyCallBack callBack = new MyCallBack();  
        asyncClient.add(10, 20, callBack);
	}
	
	public void nonBlocking() throws Exception {
		try {
			TSocket socket = new TSocket("localhost", 9090);
			TFramedTransport transport = new TFramedTransport(socket);
			TProtocol protocol = new TCompactProtocol(transport);
			TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol, "HelloWorldService");
			HelloWorldService.Client client = new HelloWorldService.Client(tMultiplexedProtocol);
			transport.open();
			System.out.println(client.sub(30, 20));
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void threadPool() throws Exception {
		TTransport transport = new TSocket("localhost", 9090);
		TProtocol protocol = new TCompactProtocol(transport);
		TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol, "HelloWorldService");
		HelloWorldService.Client client = new HelloWorldService.Client(tMultiplexedProtocol);
		transport.open();
		System.out.println(client.sub(30, 20));
		transport.close();
	}
}

class MyCallBack implements AsyncMethodCallback<add_call> {
	@Override
	public void onComplete(add_call response) {
		try {
			System.out.println(response.getResult());
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onError(Exception exception) {
		
	}
	
}
