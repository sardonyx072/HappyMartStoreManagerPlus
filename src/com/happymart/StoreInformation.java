package com.happymart;

public class StoreInformation {
	private String address;
	private String phone;
	
	public StoreInformation (String address, String phone) {
		this.setAddress(address);
		this.setPhone(phone);
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String toString() {
		return this.address + "\n" + this.phone;
	}
}
