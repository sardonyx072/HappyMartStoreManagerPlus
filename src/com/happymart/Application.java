package com.happymart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

public class Application {

	public static void main(String[] args) {
		boolean redo;
		do {
			redo = false;
			Object response = DisplayState.PROGRAM_STARTUP.showAndRequestInput();
			try {
				String[] choiceRaw = (String[])response;
				int choiceParsed = Integer.parseInt(choiceRaw[0]);
				if (choiceParsed == 1) {
					new Thread(new StoreServer()).start();
				}
				else if (choiceParsed == 2) {
					boolean badIP;
					do {
						badIP = false;
						response = DisplayState.TERMINAL_STARTUP.showAndRequestInput();
						if (response.getClass() == Boolean.class) {
							redo = true;
						}
						else {
							try {
								Socket s = new Socket(((String[])response)[0],9876);
								ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
								ObjectInputStream in = new ObjectInputStream(s.getInputStream());
								out.writeObject(new Command<String>(CommandType.GET_UNIQUE_UUID,"Creating new terminal"));
								Response<UUID> idResponse = (Response<UUID>)in.readObject();
								UUID id = idResponse.getContent();
								new Thread(new Terminal(((String[])response)[0],id)).start();
							} catch (UnknownHostException e) {
								badIP = true;
							} catch (ClassCastException e) {
								badIP = true;
							} catch (IOException e) {
								badIP = true;
							} catch (ClassNotFoundException e) {
								badIP = true;
							}
						}
					} while (!redo && badIP);
				}
			} catch (ClassCastException e) {
				
			}
		} while (redo);
	}
}
