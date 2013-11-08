package com.mycompany.service;

import javax.jws.WebService;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mycompany.domain.User;

@WebService
@Path("/user")
@Produces({"application/xml", "application/json"})
public interface UserService extends GenericService<User, Long> {

	/**
	 * Create a new user when a user signs up.
	 */
	public void createNewUser(User user);
	
	public void createNewAdmin(User user);
	
	public void updateUser(User user);
	
	public User getUserByUsername(String username);
	
	public void sendWelcomeEmail(User user);
}

