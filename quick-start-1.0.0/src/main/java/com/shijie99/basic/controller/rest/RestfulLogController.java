package com.shijie99.basic.controller.rest;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log/rest")
public class RestfulLogController {
	@SuppressWarnings("unused")
	@RequestMapping(value = "/chgg/{package}/{level}", method = RequestMethod.GET)
	public String index(@PathVariable("package") String p, @PathVariable("level") String level) {
		Level lv = Level.toLevel(level);
		Logger log = LogManager.getLogger(p);
//		log.setLevel(lv);
		return "success";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/chgp", method = RequestMethod.POST)
	public String changeLogger(String loggerName, String level) {
		Level lv = Level.toLevel(level);
		Logger log = LogManager.getLogger(loggerName);
//		log.setLevel(lv);
		return "日志级别修改成功!";
	}
}
