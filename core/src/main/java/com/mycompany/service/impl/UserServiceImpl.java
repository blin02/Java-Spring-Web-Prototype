package com.mycompany.service.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.UserDao;
import com.mycompany.domain.Role;
import com.mycompany.domain.User;
import com.mycompany.service.EmailService;
import com.mycompany.service.RoleService;
import com.mycompany.service.UserService;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService, UserDetailsService {
	
    private UserDao userDao;    
    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;
    private RoleService roleService;
    private EmailService emailService;
    
    @Autowired
    public void setRoleService(RoleService roleService) {
    	this.roleService = roleService;
    }
    
    @Autowired
    public void setEmailService(EmailService emailService) {
    	this.emailService = emailService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    	this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setSaltSource(SaltSource saltSource) {
    	this.saltSource = saltSource;
    }

    @Autowired
    public UserServiceImpl(UserDao userDao) {
    	super(userDao);       	
        this.userDao = userDao;
    }
    
    @Override
    public void createNewUser(User user) {    
    	Date today = new Date();
    	
    	//Set common properties
    	user.setEnabled(true);
    	user.setLockedOut(false);
    	user.setLastLoginDate(today);
    	user.setCreateDate(today);
    	user.setLastUpdateDate(today);
	
    	// Encode the password
    	user.setPassword(this.encodePassword(user));
    		    	
    	//Add a role to the user
    	Role role = roleService.getRoleByName("User"); 	    	
    	user.getRoles().add(role);
    	
    	this.save(user);
    }
    
	@Override
	public void createNewAdmin(User user) {
    	Date today = new Date();
    	
    	//Set common properties
    	user.setEnabled(true);
    	user.setLockedOut(false);
    	user.setLastLoginDate(today);
    	user.setCreateDate(today);
    	user.setLastUpdateDate(today);
	
    	// Encode the password
    	user.setPassword(this.encodePassword(user));
    		    	
    	//Add a role to the user
    	Role role = roleService.getRoleByName("Admin");    	
    	user.getRoles().add(role);
    	
    	this.save(user);		
	}           
    
	@Override
	public User getUserByUsername(String username) {  	
    	User user = this.userDao.getUserByUsername(username);
    	
    	return user;
    }

	@Override
	public void updateUser(User user) {		
		Date today = new Date();
    	user.setLastUpdateDate(today);
    	    	
    	if(user.getPassword().trim().equals("")) {    		
    		String currentPassword = this.userDao.getPasswordByUserId(user.getId());
    		user.setPassword(currentPassword);    		
    	} else {
        	// Encode the password
        	user.setPassword(this.encodePassword(user));    		
    	}
    	    	
    	this.update(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		return (UserDetails)this.getUserByUsername(username);
	}
	
	@Override
	public void sendWelcomeEmail(User user) {
		String fromEmailAddress = "noreply@mycompany.com";
		String subject = "Welcome";
		String bodyText = user.getFirstName() + ", thank you for registration!";
		emailService.sendEmail(fromEmailAddress, user.getEmail(), subject, bodyText, true);
	}
	
	/***
	 * Encode password based on the user information. 
	 * @param user
	 * @return encoded password in String
	 */
	private String encodePassword(User user) {		
    	// Get Salt
    	Object salt = saltSource.getSalt(user);
    	
    	// Encode the password
    	return passwordEncoder.encodePassword(user.getPassword(), salt);
	}
}