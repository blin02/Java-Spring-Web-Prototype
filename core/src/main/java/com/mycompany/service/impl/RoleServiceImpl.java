package com.mycompany.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.RoleDao;
import com.mycompany.domain.Role;
import com.mycompany.service.RoleService;

@WebService(endpointInterface = "com.mycompany.service.RoleService")
@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {
	
    private RoleDao roleDao;
    
    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
    	super(roleDao);       	
        this.roleDao = roleDao;
    }

	@Override
	public Role getRoleByName(String roleName) {
		this.roleDao.getRoleByName(roleName);
		return null;
	}
           
}