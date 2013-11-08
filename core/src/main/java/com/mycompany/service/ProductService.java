package com.mycompany.service;

import com.mycompany.domain.*;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@WebService
@Path("/product")
@Produces({"application/xml", "application/json"})
public interface ProductService extends GenericService<Product, Long> {

   @GET
   @Path("/getProductsByName/{productName}")
   List<Product> getProductsByName(@WebParam(name="productName") @PathParam("productName") String productName);
   
}

