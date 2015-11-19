package com.eboji.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eboji.dao.HelloDAO;
import com.eboji.service.HelloService;

@Service("helloService")
public class HelloServiceImpl implements HelloService {
	@Autowired
	private HelloDAO helloDAO;
	
	@Override
	public String hello() {
		return helloDAO.hello();
	}
}
