package com.mycompany.web.model;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class UserModel {	
	
	//@NotBlank(message = "Username is required!")  // If not using message resources properties file
	@NotBlank
	private String username;
	
	private String password;
	
	@NotBlank
	@Length(max = 100)
	private String firstName;
	
	@NotBlank
	private String lastName;
		
	@Email
	private String email;
		
	private String phoneNumber;	
	
    /**
	 * Default constructor - creates a new instance with no values set.
	 */
	public UserModel() {
				
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}        
}