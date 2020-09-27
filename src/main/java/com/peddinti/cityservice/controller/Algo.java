
package com.peddinti.cityservice.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Algo {

	public static void main(String[] args) {

		CityMap map = new CityMap();
		map.addCities("Boston", "New York");
		map.addCities("Philadelphia", "Newark");
		map.addCities("Newark", "Boston");
		map.addCities("Trenton", "Albany");

		String source = "Boston";
		String destination = "New York";
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(source);
		System.out.println(map.searchForRoute(destination, visited));
	}

}

class CityMap {
	private Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();

	public void addConnection(String citySrc, String cityDest) {
		HashSet<String> adjacent = map.get(citySrc);
		if (adjacent == null) {
			adjacent = new HashSet<String>();
			map.put(citySrc, adjacent);
		}
		adjacent.add(cityDest);
	}

	public void addCities(String citySrc, String cityDest) {
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