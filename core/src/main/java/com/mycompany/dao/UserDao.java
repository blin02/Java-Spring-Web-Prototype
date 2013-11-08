package com.mycompany.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.mycompany.domain.*;

public interface UserDao  extends GenericDao<User, Long> {

    public User getUserByUsername(String username) ;
    
    public String getPasswordByUserId(Long id) ;

}