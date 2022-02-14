package com.scorewell.dto;

import java.util.List;

import com.scorewell.common.UserPermission;

public class User extends BaseDBObject{
	private String globalkey;
	private String username;
	private String password;
	private String agency;
	private String agencySite;
	private String email;
	private String lastname;
	private String firstname;
	private String title;
	private String phone;
	private String address;
	private String city;
	private String state;
	private int zip;
	private boolean active;
	private List<UserPermission> permissions;
	private UserRole role;
	private boolean menuMinimized;
	private boolean subscribe_only;

	public String getGlobalkey() {
		return globalkey;
	}

	public void setGlobalkey(String globalkey) {
		this.globalkey = globalkey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAgencySite() {
		return agencySite;
	}

	public void setAgencySite(String agencySite) {
		this.agencySite = agencySite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<UserPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isMenuMinimized() {
		return menuMinimized;
	}

	public void setMenuMinimized(boolean menuMinimized) {
		this.menuMinimized = menuMinimized;
	}

	public boolean isSubscribe_only() {
		return subscribe_only;
	}

	public void setSubscribe_only(boolean subscribe_only) {
		this.subscribe_only = subscribe_only;
	}
}