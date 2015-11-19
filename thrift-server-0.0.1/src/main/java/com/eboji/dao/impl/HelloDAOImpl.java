package com.eboji.dao.impl;

import org.springframework.stereotype.Component;

import com.eboji.dao.HelloDAO;

@Component("helloDAO")
public class HelloDAOImpl implements HelloDAO {
	@Override
	public String hello() {
		return "hello";
	}
}
