package com.happymart;

import java.util.Date;
import java.util.UUID;

public class Session {
	private UUID id;
	private SessionInfo sessionInfo;
	private Date sessionOpenTimestamp;
	private Date sessionCloseTimestamp;
	
	public Session (UUID id, SessionInfo sessionInfo) {
		this.id = id;
		this.sessionInfo = sessionInfo;
		this.sessionOpenTimestamp = new Date();
		this.sessionCloseTimestamp = null;
	}
	
	public UUID getID() {
		return this.id;
	}
	public Employee getEmployee() {
		return this.sessionInfo.getEmployee();
	}
	public UUID getTerminalUUID() {
		return this.sessionInfo.getTerminalUUID();
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
