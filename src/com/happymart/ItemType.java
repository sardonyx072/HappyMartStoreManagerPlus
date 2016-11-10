package com.happymart;

import java.util.UUID;

public class ItemType {
	private UUID id;
	private String name;
	private String unitName;
	private int priceInUSCents;
	private String storeNotes; //can include manufacturer or supplier
	
	public ItemType (UUID id, String name, String unitName, int priceInUSCents, String storeNotes) {
		this.id = id;
		this.setName(name);
		this.setUnitName(unitName);
		this.setPriceInUSCents(priceInUSCents);
		this.setStoreNotes(storeNotes);
	}

	public UUID getID() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getPriceInUSCents() {
		return priceInUSCents;
	}
	public void setPriceInUSCents(int priceInUSCents) {
		this.priceInUSCents = priceInUSCents;
	}
	public String getStoreNotes() {
		return storeNotes;
	}
	public void setStoreNotes(String storeNotes) {
		this.storeNotes = storeNotes;
	}
	public String toString() {
		return this.name + " ($" + (this.priceInUSCents/100.0) + "/" + this.unitName + ")";
	}
}
