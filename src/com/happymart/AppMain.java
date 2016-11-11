package com.happymart;

import java.util.Scanner;

public class AppMain {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Launchable app;
		int selection;
		boolean ok = false;
		do {
			try {
				System.out.println("\u001b[2J");
				System.out.println("Launch application as:");
				System.out.println("1. Server");
				System.out.println("2. Register");
				System.out.println("Selection: ");
				selection = scan.nextInt();
				ok = true;
				switch (selection) {
				case 1:
					app = new AppServer();
					app.launch(app);
				case 2:
					app = new AppRegister();
					app.launch(app);
				}
			} catch (Exception e) {}
		} while (!ok);
	}
}
