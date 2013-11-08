package com.mycompany.service.impl;

import com.mycompany.dao.*;
import com.mycompany.domain.*;
import com.mycompany.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@WebService(endpointInterface = "com.mycompany.service.ProductService")
@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {
	
    private ProductDao productDao;
    
    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
    	super(productDao);       	
        this.productDao = productDao;
    }
    
    public List<Product> getProductsByName(String productName) {    	
    	log.debug("product name: {}", productName);
    	
    	return productDao.getProductsByName(productName);
    }
    
}