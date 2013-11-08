package com.mycompany.service;

import javax.jws.*;
import javax.ws.rs.*;

import com.mycompany.domain.weather.*;

@WebService
@Path("/weather")
@Produces({"application/xml", "application/json"})
public interface WeatherService {
	
    @GET
    @Path("/getCityForecastByZip/{zip}")
    WeatherInfo getCityForecastByZip (@WebParam(name="zip") @PathParam("zip") String zip);    
}