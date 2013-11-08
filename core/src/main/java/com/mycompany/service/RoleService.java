package com.mycompany.service;

import javax.jws.WebService;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mycompany.domain.*;

@WebService
@Path("/role")
@Produces({"application/xml", "application/json"})
public interface RoleService extends GenericService<Role, Long> {

	public Role getRoleByName(String roleName);

}

