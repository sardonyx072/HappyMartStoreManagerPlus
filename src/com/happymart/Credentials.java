package com.happymart;

import java.io.Serializable;

public class Credentials implements Serializable {
	private String username;
	private String password;
	
	public Credentials (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean equals(Credentials other) {
		return this.username.equals(other.username) && this.password.equals(other.password);
	}
	public String toString() {
		return "{" + this.username + "," + this.password + "}";
	}
}
