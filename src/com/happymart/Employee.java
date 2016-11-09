package com.happymart;

import java.util.Date;
import java.util.UUID;

public class Employee {
	private UUID id;
	private Name name;
	private Credentials credentials;
	private Date employeeSince;
	
	public Employee (UUID id, Name name, String username) {
		this.id = id;
		this.name = name;
		this.credentials = new Credentials(username,"pw");
		this.employeeSince = new Date();
	}
	
	public UUID getID() {
		return this.id;
	}
	public String getFirstName() {
		return this.name.getFirstName();
	}
	public void setFirstName(String firstName) {
		this.name.setFirstName(firstName);
	}
	public String getMiddleNameOrInitial() {
		return this.name.getMiddleNameOrInitial();
	}
	public void setMiddleNameOrInitial(String middleNameOrInitial) {
		this.name.setMiddleNameOrInitial(middleNameOrInitial);
	}
	public String getLastName() {
		return this.name.getLastName();
	}
	public void setLastName(String lastName) {
		this.name.setLastName(lastName);
	}
	public String getLegalName() {
		return this.name.getLegalName();
	}
	public String getShortName() {
		return this.name.getShortName();
	}
	public String getDictName() {
		return this.name.getDictName();
	}
	public String getUsernamePrefix() {
		return this.getUsername().replaceAll("[0-9]", "");
	}
	public String getUsername() {
		return this.credentials.getUsername();
	}
	public void setUsername(String username) {
		this.credentials.setUsername(username);
	}
	public String getPassword() {
		return this.credentials.getPassword();
	}
	public void setPassword(String password) {
		this.credentials.setPassword(password);
	}
	public Date getEmployeeSince() {
		return employeeSince;
	}
	public String toString() {
		return this.getLegalName();
	}
}
