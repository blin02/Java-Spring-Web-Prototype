package com.mycompany.test.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.domain.Product;
import com.mycompany.test.integration.IntegrationBaseTest;
import com.mycompany.service.ProductService;

public class ProductServiceIntegrationTest extends IntegrationBaseTest {
	
	@Autowired
	private ProductService productService;
	
	private static final long Existing_Product_Id = 1L;
	
	@Before
	public void setUp() {		


	}
	
	@Test
	public void testGetProduct() {		
		Product product = productService.get(Existing_Product_Id);
		assertNotNull(product);		
	}


}
