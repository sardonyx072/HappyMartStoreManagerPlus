package com.happymart;

public class SessionReport extends Report {
	private Session session;
	
	public SessionReport (Session session) {
		this.session = session;
		
		StringBuilder builder = new StringBuilder();
		builder.append("Report for");
		builder.append(" register " + this.session.getTerminalUUID());
		builder.append(" session " + this.session.getID());
		builder.append(" for " + this.session.getEmployee());
		builder.append('\n');
		builder.append("Generated on " + this.timestamp);
		builder.append('\n');
		builder.append(this.session);
		this.generatedReport = builder.toString();
	}
}
