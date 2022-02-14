package com.scorewell.dto;

import com.scorewell.utils.StringUtils;

public class Message {

	private String name;
	private String email;
	private String subject;
	private String message;
	private String ip;
	private boolean status;
	private long datetime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDatetime() {
		return StringUtils.formatDate(this.datetime, "dd-MM-yyyy");
	}
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
