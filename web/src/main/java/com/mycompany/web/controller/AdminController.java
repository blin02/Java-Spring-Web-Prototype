package com.mycompany.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.domain.Product;
import com.mycompany.domain.User;
import com.mycompany.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin*")
public class AdminController extends BaseController {
		
	@Autowired
	private UserService userService = null;

	
	@RequestMapping(value = {"", "/"}, method = {RequestMethod.GET})
	public String home(Locale locale, Model model) {		
				
		return "admin/home";
	}
	
    @RequestMapping(value = {"manageUsers"}, method = {RequestMethod.GET})
    public ModelAndView getAllProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
        	
    	List<User> users = this.userService.getAll();
    	                
        return new ModelAndView("admin/manageUsers", "users", users);
    }
	
}
