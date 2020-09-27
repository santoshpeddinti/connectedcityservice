package com.peddinti.cityservice.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Configuration
//@PropertySource("classpath:application.properties")
@Component
public class ServiceProperties {
	
	@Value("${connectedPath}")
	private String connectedPath;

	@Value("${addCities}")
	private String addCities;
	
	@Value("${baseUrl}")
	private String baseUrl;

	public String getConnectedPath() {
		return connectedPath;
	}

	public String getAddCities() {
		return addCities;
	}

	public String getBaseUrl() {
		return baseUrl;
	}
}
