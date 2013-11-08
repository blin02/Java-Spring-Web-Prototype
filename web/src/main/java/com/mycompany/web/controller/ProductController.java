package com.mycompany.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.service.*;
import com.mycompany.domain.*;

@Controller
@RequestMapping("/product*")
public class ProductController extends BaseController{
		
	private ProductService productService = null;
	
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
            		
    @RequestMapping(value = {"", "/", "getAll"}, method = {RequestMethod.GET})
    public ModelAndView getAllProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	List<Product> products = this.productService.getAll();
    	                
        return new ModelAndView("product/getAll", "products", products);
    }
	
    @RequestMapping(value = "search", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getProductsByName(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	List<Product> products = null;
    	    	
    	if( request.getMethod().equalsIgnoreCase("post"))
    	{
    		String productName = request.getParameter("productName");
        	products = this.productService.getProductsByName(productName);    		    		    		    	
    	}
    	
        return new ModelAndView("product/search", "products", products);    	
    }

}
