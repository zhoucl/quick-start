package it.pkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.pkg.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
