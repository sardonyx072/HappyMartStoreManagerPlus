package com.happymart;

import java.util.Vector;

public class StoreReport extends Report {
	private Vector<Session> sessions;
	
	public StoreReport (Vector<Session> sessions) {
		this.sessions = sessions;
		
		StringBuilder builder = new StringBuilder();
		builder.append("Report for today");
		builder.append('\n');
		builder.append("Generated on " + this.timestamp);
		builder.append('\n');
		for (Session i : this.sessions) {
			builder.append("\n" + i);
		}
		this.generatedReport = builder.toString();
	}
}
