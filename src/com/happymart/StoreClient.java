package com.happymart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class StoreClient {
	public static boolean command (String serverIP, Command<?> command, Object response) {
		try {
			Socket connection = new Socket(serverIP,9876);
			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
			out.writeObject(command);
			Response<?> back = (Response<?>)in.readObject();
			if (back.getType() == ResponseType.SUCCESS) {
				response = back.getContent();
			}
			else {
				return false;
			}
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
}
