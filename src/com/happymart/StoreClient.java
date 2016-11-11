package com.happymart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class StoreClient {
	public static Object command (String serverIP, Command<?> command) {
		try {
			Socket connection = new Socket(serverIP,9876);
			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
			out.writeObject(command);
			Response<?> back = (Response<?>)in.readObject();
			if (back.getType() == ResponseType.SUCCESS) {
				return back.getContent();
			}
			else {
				return new Boolean(false);
			}
		} catch (UnknownHostException e) {
			return new Boolean(false);
		} catch (IOException e) {
			return new Boolean(false);
		} catch (ClassNotFoundException e) {
			return new Boolean(false);
		}
	}
}
