package com.happymart;

public class ItemQuantity {
	private ItemType type;
	private int quantity;
	
	public ItemQuantity (ItemType type, int quantity) {
		this.type = type;
		this.quantity = quantity;
	}
	public ItemType getItem() {
		return this.getItem();
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	public void subtractQuantity(int quantity) {
		this.quantity -= quantity;
	}
	public int getTotalWorthInUSCents() {
		return this.quantity*this.type.getPriceInUSCents();
	}
	public String toString() {
		return this.quantity + " x " + this.type.toString();
	}
}
