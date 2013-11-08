package com.mycompany.dao;

import com.mycompany.domain.Role;

public interface RoleDao  extends GenericDao<Role, Long> {

	public Role getRoleByName(String roleName);

}