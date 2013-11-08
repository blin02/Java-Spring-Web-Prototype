package com.mycompany.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.GenericDao;

public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected Class<T> persistentClass;
    protected SessionFactory sessionFactory;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	public List<T> getAll() {
    	Criteria criteria = this.getCurrentSession().createCriteria(persistentClass);
    	return criteria.list();    	
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	public T get(PK id) {   	
        Object obj = this.getCurrentSession().get(this.persistentClass, id);
        return (T)obj;
    }

    @Transactional
    public T saveOrUpdate(T object) {   	
    	this.getCurrentSession().saveOrUpdate(object);    	
    	return object;
    }

    @Transactional
    public void remove(PK id) {
    	this.getCurrentSession().delete(this.get(id));
    }

    public boolean exists(PK id) {    	    	
    	if(this.get(id) != null) 
    		return true;
    	else
    		return false;    	
    }
}
