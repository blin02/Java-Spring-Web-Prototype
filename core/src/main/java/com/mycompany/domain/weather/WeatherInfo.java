package com.mycompany.domain.weather;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.*;
import org.dozer.Mapping;

@XmlRootElement
public class WeatherInfo implements Serializable{
	
	private static final long serialVersionUID = -748269334050306963L;
	
	private Long id;
	private String city;
	private String state;
	
	@Mapping("forecastResult.forecast")
	private List<Forecast> forecast;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }	
	
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) { 
        this.city = city;
    }	

    public String getState() {
        return this.state;
    }

    public void setState(String state) { 
        this.state = state;
    }

	@XmlElementWrapper(name="listOfForecast")
	public List<Forecast> getForecast() {
        return this.forecast;
    }

    public void setForecast(List<Forecast> forecast) { 
        this.forecast = forecast;
    }	
}
