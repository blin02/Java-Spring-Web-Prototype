package com.mycompany.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.domain.User;
import com.mycompany.service.UserService;
import com.mycompany.web.model.UserModel;
import com.mycompany.web.validator.UserValidator;

@Controller
@RequestMapping("/user*")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService = null;
	
	@Autowired 
	private AuthenticationManager authenticationManager; 
	
    @Autowired  
    private Validator validator;  
	    
    @RequestMapping(value = {"", "/"}, method = {RequestMethod.GET})
    public String index() {
    	return "redirect:user/profile";
    }
        
    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String showLogin() {
    	return "user/login";
    }
    
    @RequestMapping(value = "signup", method = {RequestMethod.GET})
    public ModelAndView showSignupForm(UserModel user) throws Exception{    	    	
    	ModelMap model = new ModelMap();
    	model.addAttribute("user", user);    	
    	
    	return new ModelAndView("user/signup", model);
    }

    /**
     * Process the signup form. It validates the data and creates a new user.
     * @param request
     * @param response
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "signup", method = {RequestMethod.POST})
    public ModelAndView submitSignupForm(HttpServletRequest request, 
    		HttpServletResponse response, @ModelAttribute("user") UserModel user,
    		BindingResult result) throws Exception{    	   		

		log.debug("username: {}", user.getUsername());
		
    	//validate form
    	validator.validate(user, result);
    	
    	if(!result.hasErrors()) {
	    	Mapper mapper = new DozerBeanMapper();	    		    	
	    	User domainUser=  mapper.map(user, User.class);
			
    		userService.createNewUser(domainUser);    		
    		
    		//Send welcome email if user provides an email address
    		if(!user.getEmail().equals("")) {
    			userService.sendWelcomeEmail(domainUser);
    		}
    		
    		this.setAthenticationToken(user.getUsername(), user.getPassword(), request);
    		
    		return new ModelAndView("redirect:/product/getAll");
    	}
		    	    	
    	return new ModelAndView("user/signup");
    }
        
    @RequestMapping(value = "profile", method = {RequestMethod.GET})
    public ModelAndView showProfileForm(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
    	
    	String username = request.getUserPrincipal().getName();    	
    	User domainUser = this.userService.getUserByUsername(username);
    	
    	Mapper mapper = new DozerBeanMapper();	    		    	
    	UserModel user = mapper.map(domainUser, UserModel.class);
    	
    	ModelMap model = new ModelMap();
    	model.addAttribute("user", user);    	
    	
    	return new ModelAndView("user/profile", model);
    }
    
    @RequestMapping(value = "profile", method = {RequestMethod.POST})
    public ModelAndView submitProfileForm(HttpServletRequest request, 
    		HttpServletResponse response, @ModelAttribute("user") UserModel user,
    		BindingResult result) throws Exception{

    	ModelMap model = new ModelMap();
    	model.addAttribute("user", user);

    	//use UserValidator to validate
    	//UserValidator validator = new UserValidator();
    	
    	//validate form
    	validator.validate(user, result);
    	
    	if(!result.hasErrors()) {
    		        	    	
        	User domainUser = this.userService.getUserByUsername(user.getUsername());    	

        	//Load the updated user information to domain user object
        	Mapper mapper = new DozerBeanMapper();	    		    	
        	mapper.map(user, domainUser);
        	       
        	this.userService.updateUser(domainUser);    		
    	}
    	
    	return new ModelAndView("user/profile", model);
    }
    
    /**
     * Set authentication token (used to log user in)
     * @param user
     */
    protected void setAthenticationToken(String username, String password, HttpServletRequest request) {
    	   	
        // log user in automatically
    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);    	
    	request.getSession();         
    	authToken.setDetails(new WebAuthenticationDetails(request));        
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);                
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        
        log.debug("authenticated: {}", authenticatedUser.isAuthenticated());
        log.debug("Logging in with [{}]", authenticatedUser.getAuthorities()); 
    }
    
}
