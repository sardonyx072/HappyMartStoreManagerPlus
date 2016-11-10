package com.happymart;

import java.util.UUID;

public class ItemQuantityManaged extends ItemQuantity {
	private int minimum;
	
	public ItemQuantityManaged (ItemType type, int quantity, int minimum) {
		super(type,quantity);
		this.setMinimum(minimum);
	}

	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	//TODO: return amount to order when quantity adjusted
	
}
