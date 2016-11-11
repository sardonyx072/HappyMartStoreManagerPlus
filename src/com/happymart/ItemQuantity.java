package com.happymart;

import java.io.Serializable;
import java.util.UUID;

public class ItemQuantity implements Serializable {
	private UUID id;
	private ItemType type;
	private int quantity;
	
	public ItemQuantity (ItemType type, int quantity) {
		this.type = type;
		if (quantity >= 0) {
			this.quantity = quantity;
		}
		else {
			throw new IllegalArgumentException("Cannot have a negative quantity!");
		}
	}
	public ItemType getItemType() {
		return this.type;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		if (quantity >= 0) {
			this.quantity = quantity;
		}
		else {
			throw new IllegalArgumentException("Cannot have a negative quantity!");
		}
	}
	public void addQuantity(int quantity) {
		if (quantity >= 0) {
			this.quantity += quantity;
		}
		else {
			throw new IllegalArgumentException("Cannot add a negative quantity!");
		}
	}
	public void subtractQuantity(int quantity) {
		if (quantity >= 0) {
			if (this.quantity - quantity >= 0) {
				this.quantity -= quantity;
			}
			else {
				throw new IllegalArgumentException("Cannot have a negative quantity!");
			}
		}
		else {
			throw new IllegalArgumentException("Cannot subtract a negative quantity!");
		}
	}
	public int getTotalWorthInUSCents() {
		return this.quantity*this.type.getPriceInUSCents();
	}
	public String toString() {
		return this.quantity + " x " + this.type.toString();
	}
}
