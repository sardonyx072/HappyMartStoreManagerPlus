package com.happymart;

import java.util.Date;

public class PerishableItemType extends ItemType {
	private Date expirationDate;
	
	public PerishableItemType (String name, String unitName, int priceInUSCents, Date expirationDate, String storeNotes) {
		super(name,unitName,priceInUSCents,storeNotes);
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
