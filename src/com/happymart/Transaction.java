package com.happymart;

import java.util.Map;
import java.util.UUID;

public class Transaction extends Activity {
	private UUID id;
	private UUID[] referencedTransactionIDs;
	private String storeInfo;
	private long registerID;
	private String employee;
	private Map <ItemType,Integer> purchasedItems;
	private int purchasedSubtotal;
	private Map <ItemType,Integer> returnedItems;
	private int returnedSubtotal;
	private int total;
	private PaymentType paymentType;
	private String paymentString;
	
	public Transaction (UUID[] referencedIDs, String storeInfo, long registerID, String employee, Map<ItemType,Integer> purchasedItems, Map<ItemType,Integer> returnedItems, PaymentType type, String paymentString) {
		super(ActivityType.Transaction,"Transaction");
		this.id = UUID.randomUUID();
		this.referencedTransactionIDs = referencedIDs;
		this.storeInfo = storeInfo;
		this.registerID = registerID;
		this.employee = employee;
		this.purchasedItems = purchasedItems;
		this.purchasedSubtotal = 0;
		for (ItemType key : purchasedItems.keySet()) {
			this.purchasedSubtotal += purchasedItems.get(key).intValue();
		}
		this.returnedItems = returnedItems;
		for (ItemType key : returnedItems.keySet()) {
			this.returnedSubtotal += returnedItems.get(key).intValue();
		}
		this.total = this.purchasedSubtotal - this.returnedSubtotal;
		this.paymentType = type;
		this.paymentString = paymentString;
	}
	public UUID getID() {
		return this.id;
	}
	public UUID[] getReferencedTransactionIDs() {
		return this.referencedTransactionIDs;
	}
	public String getStoreInfo() {
		return this.storeInfo;
	}
	public long getRegisterID() {
		return this.registerID;
	}
	public String getEmployee() {
		return this.employee;
	}
	public Map<ItemType,Integer> getPurchasedItems() {
		return this.purchasedItems;
	}
	public int getPurchasedSubtotal() {
		return this.purchasedSubtotal;
	}
	public Map<ItemType,Integer> getReturnedItems() {
		return this.returnedItems;
	}
	public int getReturnedSubtotal() {
		return this.returnedSubtotal;
	}
	public int getTotal() {
		return this.total;
	}
	public PaymentType getPaymentType() {
		return this.paymentType;
	}
	public String getPaymentString() {
		return this.paymentString;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction completed: " + this.getDateTimestamp());
		builder.append('\n');
		builder.append(" " + this.getStoreInfo());
		builder.append('\n');
		builder.append(" " + this.getRegisterID());
		builder.append('\n');
		builder.append(" Cashier: " + this.employee);
		builder.append('\n');
		if (this.getReferencedTransactionIDs().length > 0) {
			builder.append(" Referenced receipt numbers:");
			for (UUID x : this.getReferencedTransactionIDs()) {
				builder.append('\n');
				builder.append(x);
			}
		}
		builder.append('\n');
		builder.append("--------");
		builder.append('\n');
		builder.append(" Purchased:");
		for (ItemType key : this.getPurchasedItems().keySet()) {
			builder.append('\n');
			builder.append("  " + (this.getPurchasedItems().get(key).intValue() * key.getPriceInUSCents()) + " = " + this.getPurchasedItems().get(key).intValue() + " x " + key);
		}
		if (!this.getReturnedItems().keySet().isEmpty()) {
			builder.append('\n');
			builder.append(" " + this.getPurchasedSubtotal());
			builder.append('\n');
			builder.append("--------");
			builder.append('\n');
			for (ItemType key : this.getReturnedItems().keySet()) {
				builder.append('\n');
				builder.append("  " + (this.getReturnedItems().get(key).intValue() * key.getPriceInUSCents()) + " = " + this.getReturnedItems().get(key).intValue() + " x " + key);
			}
			builder.append('\n');
			builder.append(" " + this.getReturnedSubtotal());
			builder.append('\n');
			builder.append("--------");
		}
		builder.append('\n');
		builder.append(" " + this.getTotal() + " TOTAL");
		builder.append('\n');
		builder.append("Paid with " + this.getPaymentType() + ": " + this.getPaymentString());
		return builder.toString();
	}
}
