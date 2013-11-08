package com.mycompany.test.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.annotation.ExpectedException;

import com.mycompany.dao.ProductDao;
import com.mycompany.domain.Product;
import com.mycompany.service.impl.ProductServiceImpl;
import com.mycompany.test.unit.BaseTest;
import com.mycompany.service.ProductService;

public class ProductServiceTest extends BaseTest{
	
	private ProductService productService;	
	private ProductDao productDao;
	private Product existingProduct;
	private Product newProduct;
	
	private static final long Existing_Product_Id = 1L;
	private static final String Search_Product_Name = "Microsoft";
	private static final String Search_Non_Existing_Product_Name = "nonexisting product name";
	
	@Before
	public void setUp() {		
		productDao = EasyMock.createMock(ProductDao.class);		
		productService = new ProductServiceImpl(productDao);
		
		//New product
		Date today = new Date();
		newProduct = new Product();
		newProduct.setName("new product name" );
		newProduct.setDescription("new product description");
		newProduct.setCreateDate(today);
		newProduct.setLastUpdateDate(today);
		
		//Assumed Existing product
		existingProduct = new Product();
		existingProduct.setId(Existing_Product_Id);
		existingProduct.setName("new product name" );
		existingProduct.setDescription("new product description");
		existingProduct.setCreateDate(today);
		existingProduct.setLastUpdateDate(today);		
	}
	
	@Test
	public void testGetProduct() {
		
		EasyMock.expect(productDao.get(Existing_Product_Id)).andReturn(existingProduct);
		EasyMock.replay(productDao);
		
		Product product = productService.get(Existing_Product_Id);
		assertNotNull(product);
		EasyMock.verify(productDao);
	}


	@Test
	public void testUpdateProduct() {		
		Product product = existingProduct;
		
		String updatedName = "updated product name";
		String updatedDescription = "updated product description";
		
		product.setName(updatedName);
		product.setDescription(updatedDescription);
		
		EasyMock.expect(productDao.saveOrUpdate(product)).andReturn(product);
		EasyMock.replay(productDao);

		productService.update(product);		
		assertEquals(updatedName, product.getName());
		assertEquals(updatedDescription, product.getDescription());		
		
		EasyMock.verify(productDao);
	}

	
	@Test
	public void testAddAndRemoveProduct() {
		
		// Add new product
		Product product = newProduct;
		product.setName("new product name" );
		product.setDescription("new product description");
		
		long id = 2L;		
		
		EasyMock.expect(productDao.saveOrUpdate(product)).andReturn(product);		
		EasyMock.expect(productDao.get(id)).andReturn(product);
		productDao.remove(id);
		//EasyMock.expect(productDao.get(id)).andThrow(new ObjectRetrievalFailureException(Product.class, id));
		EasyMock.expect(productDao.get(id)).andReturn(null);
		EasyMock.replay(productDao);

		productService.save(product);
		assertNotNull(productService.get(id));
		productService.remove(id);
		product = productService.get(id);		
		assertNull(product);
		
		EasyMock.verify(productDao);		
	}
	
	@Test
	public void testGetProductsByName() {		
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(new Product());
		List<Product> emptyProductList = new ArrayList<Product>();
		
		EasyMock.expect(productDao.getProductsByName(Search_Product_Name)).andReturn(productList);
		EasyMock.expect(productDao.getProductsByName(Search_Non_Existing_Product_Name)).andReturn(emptyProductList);
		EasyMock.replay(productDao);
				
		assertFalse(productService.getProductsByName(Search_Product_Name).isEmpty());		
		assertTrue(productService.getProductsByName(Search_Non_Existing_Product_Name).isEmpty());
		EasyMock.verify(productDao);
	} 


}
