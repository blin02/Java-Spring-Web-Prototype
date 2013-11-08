package com.mycompany.test.unit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.domain.Product;
import com.mycompany.test.unit.BaseTest;
import com.mycompany.dao.ProductDao;

public class ProductDaoTest extends BaseTest{
	
	@Autowired
	private ProductDao productDao;
	
	private static final long Existing_Product_Id = 1L;
	private static final String Search_Product_Name = "Microsoft";
	private Product newProduct;
	
	@Before
	public void setUp() {		

		Date today = new Date();
		newProduct = new Product();
		newProduct.setName("new product name" );
		newProduct.setDescription("new product description");
		newProduct.setCreateDate(today);
		newProduct.setLastUpdateDate(today);
	}
	
	@Test
	public void testGetProduct() {
		Product product = productDao.get(Existing_Product_Id);		
		assertNotNull(product);
	}
		
	@Test
	public void testAddAndUpdateAndRemoveProduct() {		
		//Add a new product
		Product product = productDao.saveOrUpdate(newProduct);
				
		//Update product
		String updatedName = "updated product name";
		String updatedDescription = "updated product description";
		Date today = new Date();
		
		product.setName(updatedName);
		product.setDescription(updatedDescription);		
		product.setLastUpdateDate(today);
		
		productDao.saveOrUpdate(product);		
		
		long id = product.getId();
		product = productDao.get(id);
		
		assertEquals(updatedName, product.getName());
		assertEquals(updatedDescription, product.getDescription());
		
		// Remove product
		productDao.remove(id);		
		product = productDao.get(id);		
		assertNull(product);
	}
	
	@Test
	public void testGetProductsByName() {		
		List<Product> products = productDao.getProductsByName(Search_Product_Name);
						
		assertNotNull(products);
		assertFalse(products.isEmpty());		
	} 

}
