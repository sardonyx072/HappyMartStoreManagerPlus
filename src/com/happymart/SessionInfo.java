package com.happymart;

import java.io.Serializable;
import java.util.UUID;

public class SessionInfo implements Serializable {
	private Employee employee;
	private UUID terminalUUID;
	
	public SessionInfo (Employee employee, UUID terminalUUID) {
		this.employee = employee;
		this.terminalUUID = terminalUUID;
	}

	public Employee getEmployee() {
		return employee;
	}
	public UUID getTerminalUUID() {
		return terminalUUID;
	}
}
