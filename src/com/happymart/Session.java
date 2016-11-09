package com.happymart;

import java.util.Date;
import java.util.UUID;

public class Session {
	private Employee employee;
	private UUID terminalUUID;
	private Date sessionOpenTimestamp;
	private Date sessionCloseTimestamp;
	
	public Session (Employee employee, UUID terminalUUID) {
		this.employee = employee;
		this.terminalUUID = terminalUUID;
		this.sessionOpenTimestamp = new Date();
		this.sessionCloseTimestamp = null;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}
	public UUID getTerminalUUID() {
		return this.terminalUUID;
	}
	public Date getSessionOpenTimestamp() {
		return this.sessionOpenTimestamp;
	}
	public Date getSessionCloseTimestamp() {
		return this.sessionCloseTimestamp;
	}
	public boolean isOpen() {
		return this.sessionCloseTimestamp == null;
	}
	public boolean closeSession() {
		boolean temp = this.isOpen();
		if (temp) {
			this.sessionCloseTimestamp = new Date();
		}
		return temp;
	}
}
