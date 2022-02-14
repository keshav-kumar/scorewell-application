package com.scorewell.dto;

public class UserSession extends BaseDBObject{
	
	private String userId;
	private String email;
	private String token;
	private boolean expired;
	private long validTill;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public long getValidTill() {
		return validTill;
	}
	public void setValidTill(long validTill) {
		this.validTill = validTill;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
