package com.happymart;

public class Name {
	private String firstName;
	private String middleNameOrInitial;
	private String lastName;
	
	//TODO: throw illegalargumentexception if any names input with 0-length
	
	public Name (String firstName, String middleNameOrInitial, String lastName) {
		this.firstName = firstName;
		this.middleNameOrInitial = middleNameOrInitial;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleNameOrInitial() {
		return this.middleNameOrInitial;
	}
	public void setMiddleNameOrInitial(String middleNameOrInitial) {
		this.middleNameOrInitial = middleNameOrInitial;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLegalName() {
		return this.firstName + (this.middleNameOrInitial.length() > 0 ? " " + this.middleNameOrInitial + " " : " ") + this.lastName; 
	}
	public String getShortName() {
		return this.firstName.substring(0, 1) + (this.middleNameOrInitial.length() > 0 ? " " + this.middleNameOrInitial.substring(0, 1) + " " : " ") + this.lastName;
	}
	public String getDictName() {
		return this.lastName + ", " + this.firstName + (this.middleNameOrInitial.length() > 0 ? " " + this.middleNameOrInitial : "");
	}
	public String toString() {
		return this.getLegalName();
	}
}
