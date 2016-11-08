package com.happymart;

import java.util.Date;

public class Employee {
	private long id;
	private String firstName;
	private String middleNameOrInitial;
	private String lastName;
	private String username;
	private String password;
	private Date employeeSince;
	
	public Employee (long id, String firstName, String middleNameOrInitial, String lastName, String username) {
		this.id = id;
		this.firstName = firstName;
		this.middleNameOrInitial = middleNameOrInitial;
		this.lastName = lastName;
		this.username = username;
		this.employeeSince = new Date();
		this.password = "pw";
	}
	public Employee (long id, String firstName, String lastName, String username) {
		this(id,firstName,"",lastName,username);
	}
	public long getID() {
		return this.id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleNameOrInitial() {
		return middleNameOrInitial;
	}
	public void setMiddleNameOrInitial(String middleNameOrInitial) {
		this.middleNameOrInitial = middleNameOrInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Date getEmployeeSince() {
		return employeeSince;
	}
	public String getLegalName() {
		return this.firstName + " " + this.middleNameOrInitial + " " + this.lastName;
	}
	public String toString() {
		return this.firstName + " " + this.middleNameOrInitial + " " + this.lastName;
	}
}
