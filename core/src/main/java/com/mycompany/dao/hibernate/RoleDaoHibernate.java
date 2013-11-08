package com.mycompany.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.RoleDao;
import com.mycompany.domain.Product;
import com.mycompany.domain.Role;

@Repository("roleDao")
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {

    public RoleDaoHibernate() {
        super(Role.class);
    }

	@Override
	@Transactional(readOnly = true)
	public Role getRoleByName(String roleName) {
    	Criteria criteria = this.getCurrentSession().createCriteria(persistentClass).
    		add(Restrictions.eq("name", roleName));
	
    	Object result = (Role)criteria.uniqueResult();
    
    	return (Role)result;
	}


}
