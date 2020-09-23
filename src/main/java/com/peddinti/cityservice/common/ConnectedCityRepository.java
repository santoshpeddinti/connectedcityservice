package com.peddinti.cityservice.common;

import java.util.List;

public class ConnectedCityRepository {
	
	private List<ConnectedCityPair> connectedRepo;

	public List<ConnectedCityPair> getConnectedRepo() {
		return connectedRepo;
	}

	public void setConnectedRepo(List<ConnectedCityPair> connectedRepo) {
		this.connectedRepo = connectedRepo;
	}
	
}
