package com.happymart;

import java.io.Serializable;
import java.util.Date;

public abstract class Activity implements Serializable {
	private ActivityType type;
	private String description;
	protected Date timestamp;
	
	public Activity (ActivityType type, String description) {
		this.type = type;
		this.description = description;
		this.timestamp = new Date();
	}
	public ActivityType getActivityType() {
		return this.type;
	}
	public String getDescription() {
		return this.description;
	}
	public Date getDateTimestamp() {
		return this.timestamp;
	}
}
