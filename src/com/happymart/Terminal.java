package com.happymart;

import java.util.UUID;

public class Terminal implements Runnable {
	private String serverIP;
	private UUID terminalID;
	private UUID sessionID;
	private Employee employee;
	
	public Terminal (String serverIP, UUID terminalID) {
		this.serverIP = serverIP;
		this.terminalID = terminalID;
	}
	
	public void run () {
		/*
		try {
			Socket s = new Socket(this.serverIP,9876);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			boolean quit = false;
			out.writeObject(obj);
			do {
				Object temp_OPEN_SESSION = DisplayState.OPEN_SESSION.showAndRequestInput();
				if (temp_OPEN_SESSION.getClass() == Boolean.class) {
					quit = true;
				}
				else {
					String[] credentialsRaw = (String[])temp_OPEN_SESSION;
					Credentials credentials = new Credentials(credentialsRaw[0],credentialsRaw[1]);
					boolean redoLogin;
					do {
						redoLogin = false;
						try {
							out.writeObject(new Command<Credentials>(CommandType.CHECK_CREDENTIALS_GENERIC,credentials));
							Response<Employee> employee = (Response<Employee>)in.readObject();
							this.employee = employee.getContent();
							out.writeObject(new Command<SessionInfo>(CommandType.OPEN_SESSION,new SessionInfo(this.employee,this.terminalID)));
						} catch (ClassCastException e) {
							redoLogin = true;
						} catch (UnknownHostException e) {
							redoLogin = true;
						} catch (IOException e) {
							redoLogin = true;
						} catch (ClassNotFoundException e) {
							redoLogin = true;
						}
					} while (redoLogin);
				}
			} while (!quit);
		} catch (IOException e) {
			
		}
		*/
		String[] credentialsRaw;
		Credentials credentials;
		boolean quitFromOpenSession = false;
		while (!quitFromOpenSession) {
			Object openSessionResponse = DisplayState.OPEN_SESSION.showAndRequestInput();
			quitFromOpenSession = openSessionResponse.getClass() == Boolean.class;
			if (!quitFromOpenSession) {
				credentialsRaw = (String[])openSessionResponse;
				boolean redoLogin;
				do {
					redoLogin = false;
					credentials = new Credentials(credentialsRaw[0],credentialsRaw[1]);
					Object credentialsResponse = StoreClient.command(this.serverIP, new Command<Credentials>(CommandType.CHECK_CREDENTIALS_GENERIC,credentials));
					if (credentialsResponse.getClass() != Boolean.class) {
						Object sessionResponse = StoreClient.command(this.serverIP, new Command<SessionInfo>(CommandType.OPEN_SESSION,new SessionInfo(this.employee,this.terminalID)));
						if (sessionResponse.getClass() != Boolean.class) {
							Object sessionHomeResponse = DisplayState.TERMINAL_HOME.showAndRequestInput(this.employee.getFirstName());
							if (sessionHomeResponse.getClass() != Boolean.class) {
								String sessionHomeChoice = ((String[])sessionHomeResponse)[0];
								if (sessionHomeChoice.equals("`")) {
									Object confirmCloseSessionResponse = DisplayState.CLOSE_SESSION.showAndRequestInput();
									if (confirmCloseSessionResponse.getClass() != Boolean.class) {
										Object closeSessionResponse = StoreClient.command(this.serverIP, new Command<UUID>(CommandType.CLOSE_SESSION,this.sessionID));
										if (closeSessionResponse.getClass() != Boolean.class) {
											
										}
									}
								}
							}
						}
						else {
							redoLogin = true;
						}
					}
					else {
						redoLogin = true;
					}
				} while (redoLogin);
			}
		}
		
	}
}
