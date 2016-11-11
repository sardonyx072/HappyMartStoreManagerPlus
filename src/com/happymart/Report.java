package com.happymart;

import java.util.Date;

public abstract class Report {
	protected Date timestamp;
	protected String generatedReport;
	
	public Report () {
		this.timestamp = new Date();
		this.generatedReport = "DEFAULT REPORT GENERATION PLACEHOLDER";
	}
	public String toString() {
		return this.generatedReport;
	}
}
