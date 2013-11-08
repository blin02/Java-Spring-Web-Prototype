package com.mycompany.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.dao.GenericDao;
import com.mycompany.domain.BaseObject;
import com.mycompany.service.GenericService;

/**
 * This should be the super class of all service classes that need to access data layer through DAO.
 *
 * @param <T>
 * @param <PK>
 */
public class GenericServiceImpl<T extends BaseObject, PK extends Serializable> implements GenericService<T, PK> {
    /**
     * Log variable for all child classes. 
     */    
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected GenericDao<T, PK> dao;

    public GenericServiceImpl() {
    }

    public GenericServiceImpl(GenericDao<T, PK> genericDao) {
    		this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() throws Exception {
    	return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
    	    	
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {    	
    	Date today = new Date();
    	object.setCreateDate(today);
    	object.setLastUpdateDate(today);
        return dao.saveOrUpdate(object);
    }
    
    public T update(T object) {    	
    	object.setLastUpdateDate(new Date());    	    	
        return dao.saveOrUpdate(object);
    }    

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.remove(id);
    }
}
