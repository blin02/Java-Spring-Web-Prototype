package com.mycompany.dao;

import java.util.List;

import com.mycompany.domain.*;

public interface ProductDao  extends GenericDao<Product, Long> {

	List<Product> getProductsByName(String productName);

}