package com.happymart;

import java.util.UUID;
import java.util.Vector;

public class RegisterReport extends Report {
	private Vector<Session> sessions;
	
	public RegisterReport (Vector<Session> sessions) throws IllegalArgumentException {
		this.sessions = sessions;
		
		UUID registerID = this.sessions.get(0).getTerminalUUID();
		
		for (Session i : sessions) {
			if (registerID != i.getTerminalUUID()) {
				throw new IllegalArgumentException("Mismatched store or register IDs in Register report generation!");
			}
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("Report for");
		builder.append(" register " + registerID);
		builder.append('\n');
		builder.append("Generated on " + this.timestamp);
		builder.append('\n');
		for (Session i : this.sessions) {
			builder.append("\n" + i);
		}
		this.generatedReport = builder.toString();
	}
}
