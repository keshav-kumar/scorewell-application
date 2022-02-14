package com.scorewell.dto;

import java.util.List;

public class UserRole extends BaseDBObject{

	private String name;
	private String homePage;
	private List<String> accessPatterns;
	private List<String> denyPatterns;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public List<String> getAccessPatterns() {
		return accessPatterns;
	}
	public void setAccessPatterns(List<String> accessPatterns) {
		this.accessPatterns = accessPatterns;
	}
	public List<String> getDenyPatterns() {
		return denyPatterns;
	}
	public void setDenyPatterns(List<String> denyPatterns) {
		this.denyPatterns = denyPatterns;
	}
	
}
