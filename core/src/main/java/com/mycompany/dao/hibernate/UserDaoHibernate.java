package com.mycompany.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.UserDao;
import com.mycompany.domain.User;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

    public UserDaoHibernate() {
        super(User.class);
    }

	@Override
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) {		
    	Criteria criteria = this.getCurrentSession().createCriteria(persistentClass)
    		.add(Restrictions.eq("username", username));

    	Object result = criteria.uniqueResult();

    	return (User)result;		
	}

	@Override
	@Transactional(readOnly = true)
	public String getPasswordByUserId(Long id) {		
		//User user = this.get(id);
		//String password = user.getPassword();
		
		Query query = this.getCurrentSession().getNamedQuery("getUserPasswordById");
		query.setParameter("id", id);
		String password = (String)query.uniqueResult();
		
		log.debug("get password by user id");
		
		return password;
	}
	
}
