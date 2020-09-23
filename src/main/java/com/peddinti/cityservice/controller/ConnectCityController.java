package com.peddinti.cityservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peddinti.cityservice.common.LoadResources;

@RestController
public class ConnectCityController {
	
	@Autowired
	LoadResources loadResources;
	
	private static final String ROUTE_CONNECTED_YES="Yes - These cities have connected route";
	private static final String ROUTE_CONNECTED_NO="No - These cities doesn't have connected route";
	private static final String ROUTE_CITIES_ADDED="Cities Added";
	
    @SuppressWarnings("resource")
	@GetMapping(path = "/connected")
    public ResponseEntity<String> getCitiesConnected(@RequestParam(name = "origin") String origin,
    		@RequestParam(name = "destination") String destination) throws IOException {
    	Path path = loadResources.getResourceStream();
    	Stream<String> inputStream = Files.lines(path);
    	String[] routeVal;
    	List<String> citiesList = inputStream.distinct().collect(Collectors.toList());
    	citiesList.forEach(System.out::println);
    	for (String route: citiesList) {
    		routeVal = route.split(",");
    		if(origin.contentEquals(routeVal[0].trim()) && destination.contentEquals(routeVal[1].trim())) {
    			return ResponseEntity.ok().body(ROUTE_CONNECTED_YES);
    		}
    	}
    	return ResponseEntity.ok().body(ROUTE_CONNECTED_NO);
    }
    
    @SuppressWarnings("resource")
	@PostMapping(path = "/connected/add")
    public ResponseEntity<String> addCities(@RequestParam(name = "origin") String origin,
    		@RequestParam(name = "destination") String destination) throws IOException {
    	Path path = loadResources.getResourceStream();
	    String textToAppend = "\r\n"+origin + "," + destination;
	    Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
    	return ResponseEntity.ok().body(ROUTE_CITIES_ADDED);
    }
}
