package com.shijie99.basic.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/log")
public class LogController {
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String logger(HttpServletRequest request, HttpServletResponse response) {
		List<String> packages = new ArrayList<String>();
		Enumeration<?> e = LogManager.getCurrentLoggers();
		while(e.hasMoreElements()) {
			Logger logger = (Logger)e.nextElement();
			packages.add(logger.getName());
		}
		
		packages.add("all");
		
		request.setAttribute("packages", packages);
		
		return "biz/logger";
	}
}
