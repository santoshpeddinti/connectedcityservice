package com.peddinti.cityservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class ConnectCityController{
	
	@Autowired
	LoadResources loadResources;
	
//	@Autowired
//	CityMapBean cityMapBean;
	
	private static final String ROUTE_CONNECTED_YES="Yes - These cities have connected route";
	private static final String ROUTE_CONNECTED_NO="No - These cities doesn't have connected route";
	private static final String ROUTE_CITIES_ADDED="Cities Added";
	private Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();

	
	@GetMapping(path = "/connected")
    public ResponseEntity<String> getCitiesConnected(@RequestParam(name = "origin") String origin,
    		@RequestParam(name = "destination") String destination) throws IOException {
		
		  Path path = loadResources.getResourceStream(); Stream<String> inputStream =
		  Files.lines(path); 
		  List<String> citiesList =
		  inputStream.collect(Collectors.toList());
		  citiesList.forEach(System.out::println);
		  String[] routeVal;
		for (String route : citiesList) {
			routeVal = route.split(",");
			addCity(routeVal[0].trim(), routeVal[1].trim());
		}
    	LinkedList<String> visited = new LinkedList<String>();
		visited.add(origin);
		if(searchForRoute(destination, visited)) {
			return ResponseEntity.ok().body(ROUTE_CONNECTED_YES); 
		}
    	return ResponseEntity.ok().body(ROUTE_CONNECTED_NO);
    }
    
	@PostMapping(path = "/connected/add")
    public ResponseEntity<String> addCities(@RequestParam(name = "origin") String origin,
    		@RequestParam(name = "destination") String destination) throws IOException {
    	Path path = loadResources.getResourceStream();
	    String textToAppend = "\r\n"+origin + "," + destination;
	    Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
    	return ResponseEntity.ok().body(ROUTE_CITIES_ADDED);
    }
    
    
	
	public void addConnection(String citySrc, String cityDest) {
		HashSet<String> adjacent = map.get(citySrc);
		if (adjacent == null) {
			adjacent = new HashSet<String>();
			map.put(citySrc, adjacent);
		}
		adjacent.add(cityDest);
	}

	public void addCity(String citySrc, String cityDest) {
		addConnection(citySrc, cityDest);
		addConnection(cityDest, citySrc);
	}

	public boolean isConnected(String node1, String node2) {
		Set<String> adjacent = map.get(node1);
		if (adjacent == null) {
			return false;
		}
		return adjacent.contains(node2);
	}

	public Set<String> connectedCities(String last) {
		Set<String> adjacent = map.get(last);
		if (adjacent == null) {
			return new HashSet<String>();
		}
		return adjacent;
	}

	public boolean searchForRoute(String dest, LinkedList<String> visited) {
		Set<String> cities = this.connectedCities(visited.getLast());

		for (String city : cities) {
			if (visited.contains(city)) {
				continue;
			}
			if (city.equals(dest)) {
				return true;
			} else {
				visited.add(city);
				return searchForRoute(dest, visited);
			}
		}
		return false;
	} 

}
