package com.happymart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StoreServer implements Runnable {
	
	private Map<UUID,Employee> employees;
	
	public StoreServer () {
		this.employees = new HashMap<UUID,Employee>();
	}
	
	@Override
	public void run() {
		ServerSocket server = null;
		Socket connection = null;
		try {
			server = new ServerSocket(9876);
			while ((connection = server.accept()) != null) {
				new Thread(new ClientHandler(connection)).start();
			}
		} catch (IOException e) {
			
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class ClientHandler implements Runnable {
		private Socket client;
		
		private ClientHandler (Socket client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			try {
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				
				Command<?> command = (Command<?>) in.readObject();
				switch (command.getType()) {
				case CREATE_EMPLOYEE:
					Name tempName = (Name)command.getContent();
					String usernamePrefix = tempName.getLastName().substring(0,Math.min(5, tempName.getLastName().length())) + tempName.getFirstName().substring(0, 1);
					int uniqueNumber = 0;
					for (Employee employee : StoreServer.this.employees.values()) {
						if (employee.getUsernamePrefix().equals(usernamePrefix)) {
							uniqueNumber++;
						}
					}
					UUID newEmployeeUUID = UUID.randomUUID();
					StoreServer.this.employees.put(newEmployeeUUID,new Employee(newEmployeeUUID,tempName,usernamePrefix+uniqueNumber));
					Response<String> response = new Response<String>(ResponseType.SUCCESS,"user " + StoreServer.this.employees.get(newEmployeeUUID).getUsername() + " created successfully!");
					out.writeObject(response);
					break;
				case UPDATE_EMPLOYEE:
					Employee tempEmployee = (Employee)command.getContent();
					break;
				case OPEN_SESSION:
					break;
				case CLOSE_SESSION:
					break;
				case LOG_ACTIVITY:
					break;
				default:
					break;
				}
				
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}