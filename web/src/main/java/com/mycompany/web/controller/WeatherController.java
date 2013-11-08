package com.mycompany.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.domain.weather.WeatherInfo;
import com.mycompany.service.WeatherService;

/**
 * @author Bin.Lin
 *
 */
@Controller
@RequestMapping("/weather*")
public class WeatherController extends BaseController{
			
	private WeatherService weatherService = null;
	
    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }	
		   
    @RequestMapping(value = {"", "/", "forecast"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showWeatherForecast(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	ModelMap model = new ModelMap();
    	
    	String zip = request.getParameter("zip");
    	
    	if (zip == null) {    		
    		zip = "11374";
    	}
    	
    	WeatherInfo weatherInfo = weatherService.getCityForecastByZip(zip);
    	model.addAttribute("weatherInfo", weatherInfo);
    
        return new ModelAndView("/weather/forecast", model);
    }
	
}
