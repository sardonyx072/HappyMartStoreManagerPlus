package com.happymart;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StoreServer implements Runnable {

	private final String filepath = "C:\\Program Files\\HappyMart";
	private StoreInformation storeInfo;
	private Map<UUID,Employee> employees;
	private Map<UUID,Session> openSessions;
	private Map<ItemType,ItemQuantityManaged> inventory;
	
	public StoreServer () {
		this.employees = new HashMap<UUID,Employee>();
		this.openSessions = new HashMap<UUID,Session>();
		this.inventory = new HashMap<ItemType,ItemQuantityManaged>();
		
		FileInputStream fileIn;
		ObjectInputStream objectIn;

		try {
			fileIn = new FileInputStream(this.filepath + "\\store\\info");
			objectIn = new ObjectInputStream(fileIn);
			this.storeInfo = (StoreInformation)objectIn.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		try {
			fileIn = new FileInputStream(this.filepath + "\\store\\employees");
			objectIn = new ObjectInputStream(fileIn);
			this.employees = (HashMap<UUID,Employee>)objectIn.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		try {
			fileIn = new FileInputStream(this.filepath + "\\store\\inventory");
			objectIn = new ObjectInputStream(fileIn);
			this.inventory = (HashMap<ItemType,ItemQuantityManaged>)objectIn.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		
		if (this.storeInfo == null) {
			this.storeInfo = new StoreInformation("123 Sunny Lane\nAnywhere MN, 12345","123-456-7890");
		}
		if (this.employees.size() == 0) {
			UUID temp = this.getUniqueUUID();
			this.employees.put(temp, new Employee(temp,new Name("admin","","admin"),"admin"));
		}
		if (this.inventory.size() == 0) {
			ItemQuantityManaged temp1 = new ItemQuantityManaged(new ItemType(this.getUniqueUUID(),"Silver Fork","fork",599,""),30,10);
			ItemQuantityManaged temp2 = new ItemQuantityManaged(new ItemType(this.getUniqueUUID(),"Electrical Outlet","module",995,""),50,25);
			this.inventory.put(temp1.getItemType(), temp1);
			this.inventory.put(temp2.getItemType(), temp2);
		}
	}
	
	private synchronized UUID getUniqueUUID() {
		return UUID.randomUUID();
	}
	
	@Override
	public void run() {
		ServerSocket server = null;
		Socket connection = null;
		ObjectOutputStream objectOut = null;
		FileOutputStream fileOut = null;
		
		//run server
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
		
		//shutdown, save server state
		try {
			fileOut = new FileOutputStream(this.filepath + "\\store\\info");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.storeInfo);
			fileOut = new FileOutputStream(this.filepath + "\\store\\employees");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.employees);
			fileOut = new FileOutputStream(this.filepath + "\\store\\inventory");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.inventory);
			objectOut.close();
		} catch (IOException e) {
			
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
				Response<?> response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could understand type!");
				
				Command<?> command = (Command<?>) in.readObject();
				switch (command.getType()) {
				case CHECK_STORE:
					response = new Response<StoreInformation>(ResponseType.SUCCESS,StoreServer.this.storeInfo);
					break;
				case UPDATE_STORE:
					try {
						StoreInformation temp_UPDATE_STORE = (StoreInformation)command.getContent();
						StoreServer.this.storeInfo = temp_UPDATE_STORE;
						response = new Response<String>(ResponseType.SUCCESS,"Store information updated successfully!");
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case CREATE_EMPLOYEE:
					try {
						Name temp_CREATE_EMPLOYEE = (Name)command.getContent();
						String usernamePrefix = temp_CREATE_EMPLOYEE.getLastName().substring(0,Math.min(5, temp_CREATE_EMPLOYEE.getLastName().length())) + temp_CREATE_EMPLOYEE.getFirstName().substring(0, 1);
						int uniqueNumber = 0;
						for (Employee employee : StoreServer.this.employees.values()) {
							if (employee.getUsernamePrefix().equals(usernamePrefix)) {
								uniqueNumber++;
							}
						}
						UUID newEmployeeUUID = StoreServer.this.getUniqueUUID();
						StoreServer.this.employees.put(newEmployeeUUID,new Employee(newEmployeeUUID,temp_CREATE_EMPLOYEE,usernamePrefix+uniqueNumber));
						response = new Response<String>(ResponseType.SUCCESS,"user " + StoreServer.this.employees.get(newEmployeeUUID).getUsername() + " created successfully!");
						
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case UPDATE_EMPLOYEE:
					try {
						Employee temp_UPDATE_EMPLOYEE = (Employee)command.getContent();
						StoreServer.this.employees.put(temp_UPDATE_EMPLOYEE.getID(), temp_UPDATE_EMPLOYEE);
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case CHECK_CREDENTIALS:
					try {
						Employee temp_CHECK_CREDENTIALS = (Employee)command.getContent();
						response = new Response<Boolean>(ResponseType.SUCCESS,StoreServer.this.employees.get(temp_CHECK_CREDENTIALS.getID()).getCredentials().equals(temp_CHECK_CREDENTIALS.getCredentials()));
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case OPEN_SESSION:
					try {
						SessionInfo temp_OPEN_SESSION = (SessionInfo)command.getContent();
						UUID newSessionUUID = StoreServer.this.getUniqueUUID();
						StoreServer.this.openSessions.put(newSessionUUID, new Session(newSessionUUID,temp_OPEN_SESSION));
						response = new Response<UUID>(ResponseType.SUCCESS,newSessionUUID);
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case CLOSE_SESSION:
					try {
						UUID temp_CLOSE_SESSION = (UUID)command.getContent();
						StoreServer.this.openSessions.get(temp_CLOSE_SESSION).closeSession();
						ObjectOutputStream sessionOut = new ObjectOutputStream(new FileOutputStream(StoreServer.this.filepath + "\\store\\sessions\\"+StoreServer.this.openSessions.get(temp_CLOSE_SESSION).getID()));
						sessionOut.writeObject(StoreServer.this.openSessions.get(temp_CLOSE_SESSION));
						sessionOut.close();
						StoreServer.this.openSessions.remove(temp_CLOSE_SESSION);
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case LOG_ACTIVITY:
					//TODO:
					break;
				case CHECK_INVENTORY:
					if (command.getContent().getClass() == UUID.class) {
						response = new Response<ItemQuantity>(ResponseType.SUCCESS,(ItemQuantity)StoreServer.this.inventory.get((UUID)command.getContent()));
					}
					else if (command.getContent().getClass() == String.class) {
						Map<ItemType,ItemQuantity> temp_CHECK_INVENTORY = new HashMap<ItemType,ItemQuantity>();
						for (ItemType key : StoreServer.this.inventory.keySet()) {
							if (StoreServer.this.inventory.get(key).getItemType().getName().contains((String)command.getContent())
									|| StoreServer.this.inventory.get(key).getItemType().getStoreNotes().contains((String)command.getContent())) {
								temp_CHECK_INVENTORY.put(key, (ItemQuantity)StoreServer.this.inventory.get(key));
							}
						}
						response = new Response<Map<ItemType,ItemQuantity>>(ResponseType.SUCCESS,temp_CHECK_INVENTORY);
					}
					else {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case ADD_QUANTITY:
					try {
						ItemQuantity temp_ADD_QUANTITY = (ItemQuantity)command.getContent();
						StoreServer.this.inventory.get(temp_ADD_QUANTITY.getItemType()).addQuantity(temp_ADD_QUANTITY.getQuantity());
						response = new Response<String>(ResponseType.SUCCESS,"Successfully updated quantity!");
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case SUBTRACT_QUANTITY:
					try {
						ItemQuantity temp_SUBTRACT_QUANTITY = (ItemQuantity)command.getContent();
						StoreServer.this.inventory.get(temp_SUBTRACT_QUANTITY.getItemType()).subtractQuantity(temp_SUBTRACT_QUANTITY.getQuantity());
						response = new Response<String>(ResponseType.SUCCESS,"Successfully updated quantity!");
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				case CHANGE_PRICE:
					try {
						ItemType temp_CHANGE_PRICE = (ItemType)command.getContent();
						StoreServer.this.inventory.get(temp_CHANGE_PRICE).getItemType().setPriceInUSCents(temp_CHANGE_PRICE.getPriceInUSCents());
						response = new Response<String>(ResponseType.SUCCESS,"Successfully updated item price!");
					} catch (ClassCastException e) {
						response = new Response<String>(ResponseType.FAILURE,"Bad command sent! Could not understand content!");
					}
					break;
				}
				out.writeObject(response);
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
