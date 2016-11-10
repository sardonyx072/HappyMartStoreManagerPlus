package com.happymart;

import java.util.Date;
import java.util.UUID;

public class PerishableItemType extends ItemType {
	private Date expirationDate;
	
	public PerishableItemType (UUID id, String name, String unitName, int priceInUSCents, Date expirationDate, String storeNotes) {
		super(id,name,unitName,priceInUSCents,storeNotes);
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
