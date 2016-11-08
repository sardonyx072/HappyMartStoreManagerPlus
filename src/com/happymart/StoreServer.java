package com.happymart;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreServer implements Runnable {
	
	public void run() {
		ServerSocket server = null;
		Socket connection = null;
		try {
			server = new ServerSocket(9876);
			while ((connection = server.accept()) != null) {
				
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
}
