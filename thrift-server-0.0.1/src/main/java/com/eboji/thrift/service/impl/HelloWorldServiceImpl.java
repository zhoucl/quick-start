package com.eboji.thrift.service.impl;

import org.apache.thrift.TException;

import com.eboji.thrift.service.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {

	@Override
	public int add(int a, int b) throws TException {
		System.out.println("----add---");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return  a + b;
	}

	@Override
	public int sub(int a, int b) throws TException {
		System.out.println("----sub---");
		return a - b;
	}

}
