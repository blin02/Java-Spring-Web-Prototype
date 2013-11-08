package com.mycompany.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.ProductDao;
import com.mycompany.domain.Product;

@Repository("productDao")
public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

    public ProductDaoHibernate() {
        super(Product.class);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByName(String productName) {    	
    	Criteria criteria = this.getCurrentSession().createCriteria(persistentClass)
    		.add(Restrictions.like("name", productName, MatchMode.ANYWHERE).ignoreCase());
    	
    	List<Product> products = criteria.list();        
        return products;
    }

}
