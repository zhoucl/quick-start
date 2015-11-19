package com.eboji.thrift.service.impl;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eboji.annotation.ThriftHandler;
import com.eboji.service.HelloService;
import com.eboji.thrift.service.HelloWorldService;

@Component
@ThriftHandler("HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService.Iface {
	@Autowired
	private HelloService helloService;
	
	@Override
	public int add(int a, int b) throws TException {
		System.out.println("----add---" + helloService.hello());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return  a + b;
	}

	@Override
	public int sub(int a, int b) throws TException {
		System.out.println("----sub---" + helloService.hello());
		return a - b;
	}

}
