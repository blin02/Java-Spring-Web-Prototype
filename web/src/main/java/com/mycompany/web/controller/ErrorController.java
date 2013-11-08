package com.mycompany.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController extends BaseController{
	
	@RequestMapping("pageNotFound")
	public String pageNotFound() {		
		return "/error/pageNotFound";		
	}

	@RequestMapping("genericError")
	public String genericError() {		
		return "/error/genericError";		
	}
	
	@RequestMapping("accessDenied")
	public String accessDenied() {		
		return "/error/accessDenied";		
	}

}
