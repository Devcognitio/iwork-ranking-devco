package com.iwork.linkedinrank.model.config;

import java.util.List;

public class LinkedInParameters {

	
	private ConnRecomParameters connections;
	private ConnRecomParameters recommendations;
	private List<DataParameters> industries;
	private List<DataParameters> roles;
	private List<DataParameters> countries;
	
	public ConnRecomParameters getConnections() {
		return connections;
	}
	public void setConnections(ConnRecomParameters connections) {
		this.connections = connections;
	}
	public ConnRecomParameters getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(ConnRecomParameters recommendations) {
		this.recommendations = recommendations;
	}
	public List<DataParameters> getIndustries() {
		return industries;
	}
	public void setIndustries(List<DataParameters> industries) {
		this.industries = industries;
	}
	public List<DataParameters> getRoles() {
		return roles;
	}
	public void setRoles(List<DataParameters> roles) {
		this.roles = roles;
	}
	public List<DataParameters> getCountries() {
		return countries;
	}
	public void setCountries(List<DataParameters> countries) {
		this.countries = countries;
	}
	
	
	
	
}
