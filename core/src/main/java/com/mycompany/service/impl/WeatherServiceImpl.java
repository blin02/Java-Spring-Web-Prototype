package com.mycompany.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cdyne.ws.weatherws.ForecastReturn;
import com.cdyne.ws.weatherws.Weather;
import com.cdyne.ws.weatherws.WeatherSoap;
import com.mycompany.domain.weather.WeatherInfo;
import com.mycompany.service.WeatherService;


@WebService(endpointInterface = "com.mycompany.service.WeatherService")
@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
		
	//private QName Service_Name;
	private URL wsdlUrl;
	private Weather weatherWebService;
	
	public WeatherServiceImpl() throws MalformedURLException {
		
		try {
			
			ResourceBundle bundle = ResourceBundle.getBundle("core-resources");
			wsdlUrl = new URL(bundle.getString("weather.wsdl.url"));
			
			log.debug("host: {}, path: {}", wsdlUrl.getHost(), wsdlUrl.getPath());
			
			//Service_Name = new QName("http://wsf.cdyne.com/WeatherWS/Weather.asmx", "Weather");
			 //wsdlUrl = new URL("http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl");
			 
			 weatherWebService = new Weather(wsdlUrl); 
			 
		} catch (MalformedURLException  ex) {
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage());		
		}
		
	}


    /**
     * {@inheritDoc}
     */
    public WeatherInfo getCityForecastByZip(String zip) {
    	    		
    	WeatherSoap weatherSoap = weatherWebService.getWeatherSoap();    	
    	ForecastReturn resp = weatherSoap.getCityForecastByZIP(zip);
    	
    	WeatherInfo weatherInfo = new WeatherInfo();
    	
    	Mapper mapper = new DozerBeanMapper();
    	weatherInfo =  mapper.map(resp, WeatherInfo.class);
    	
    	return weatherInfo;    
    }
}