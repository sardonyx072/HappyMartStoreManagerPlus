package com.happymart;

import java.util.UUID;

public class Terminal {
	private String serverIP;
	private UUID terminalID;
	private UUID sessionID;
	private Employee employee;
	
	public Terminal (String serverIP, UUID terminalID) {
		this.serverIP = serverIP;
		this.terminalID = terminalID;
	}
	
	
}
