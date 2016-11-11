package com.happymart;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Application2 {
	private final static int PORT = 9876;
	
	private Scanner scan;
	
	public static void main (String[] args) {
		Application2 app = new Application2();
		app.startup(app);
		
	}
	
	public Application2 () {
		this.scan = new Scanner(System.in);
	}
	public Application2 startup (Application2 app) {
		int selection;
		do {
			System.out.println("\u001b[2J");
			System.out.println("Start terminal as: ");
			System.out.println("1. Server: ");
			System.out.println("2. Register: ");
			System.out.println("Choice: ");
			selection = scan.nextInt();
			switch(selection) {
			case 1: return app.server();
			case 2: return app.registerStartup();
			}
		} while ();
	}
	public Application2 server (Application2 app) {
		try {
			ServerSocket server = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
