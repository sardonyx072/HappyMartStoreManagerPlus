package com.happymart;

public class ItemQuantityManaged extends ItemQuantity {
	private int minimum;
	private boolean needToOrder;
	
	public ItemQuantityManaged (ItemType type, int quantity, int minimum) {
		super(type,quantity);
		this.setMinimum(minimum);
		this.needToOrder = false;
	}

	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public boolean needToOrder() {
		return this.needToOrder;
	}
	public void setNeedToOrder(boolean set) {
		this.needToOrder = set;
	}
	@Override
	public void setQuantity(int quantity) {
		super.setQuantity(quantity);
		this.needToOrder = this.quantity < this.minimum;
	}
	@Override
	public void subtractQuantity(int quantity) {
		super.subtractQuantity(quantity);
		this.needToOrder = this.quantity < this.minimum;
	}
}
