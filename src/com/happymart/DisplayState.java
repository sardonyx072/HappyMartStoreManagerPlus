package com.happymart;

import java.util.Scanner;

public enum DisplayState {
	PROGRAM_STARTUP(
			new DisplayOptionComponent(	"Set up this terminal as:",new String[]{"Store server","Access terminal","Register"}),
			new DisplayInputComponent(	"Choice: ",InputType.OPTION,true)
			),
	SERVER_HOME(
			new DisplayTextComponent(	"Happy Mart Store Server is running."),
			new DisplayInputComponent(	"CANCEL or press [Enter] to close server. ",InputType.OK,true)
			),
	TERMINAL_STARTUP(
			new DisplayInputComponent(	"Enter IP of store server: ",InputType.TEXT,true)
			),
	OPEN_SESSION(
			new DisplayTextComponent(	"Welcome to Happy Mart Store Manager Plus!"),
			new DisplayTextComponent(	"Please sign in using your employee credentials."),
			new DisplayInputComponent(	"Username: ",InputType.TEXT,true),
			new DisplayInputComponent(	"Password: ",InputType.TEXT,true)
			),
	OPEN_SESSION_RETRY(
			new DisplayTextComponent(	"Welcome to Happy Mart Store Manager Plus!"),
			new DisplayTextComponent(	"<Either username or password was incorrect. Try again.>"),
			new DisplayTextComponent(	"Please sign in using your employee credentials."),
			new DisplayInputComponent(	"Username: ",InputType.TEXT,true),
			new DisplayInputComponent(	"Password: ",InputType.TEXT,true)
			),
	TERMINAL_LOCKED(
			new DisplayTextComponent(	"Maximum number of sign in retries exceeded. Terminal is locked.")
			),
	CLOSE_SESSION(
			new DisplayInputComponent(	"Really close session?",InputType.OK,true)
			),
	TERMINAL_HOME(
			new DisplayOptionComponent(	"What would you like to do, {{{0}}}?",new String[]{}),
			new DisplayInputComponent(	"Choice: ",InputType.OPTION,true)
			);
	
	private DisplayComponent[] components;
	
	private DisplayState (DisplayComponent... components) {
		this.components = components;
	}
	public Object showAndRequestInput(String...strings) {
		final String CANCEL = "`";
		Object result = new Boolean(false);
		boolean quit = false;
		String[] responses;
		int numInputsRequested = 0;
		int numInputsReceived = 0;
		for (DisplayComponent c : this.components) {
			if (c.getClass() == DisplayInputComponent.class) {
				numInputsRequested++;
			}
		}
		responses = new String[numInputsRequested];
		while (!quit && numInputsReceived < numInputsRequested) {
			String raw = this.collectInputFromInputNumber(numInputsReceived, responses, CANCEL, strings);
			if (raw.equals(CANCEL)) {
				--numInputsReceived;
				if (numInputsReceived >= 0) {
					responses[numInputsReceived] = null;
				}
				else {
					quit = true;
				}
			}
			else {
				responses[numInputsReceived++] = raw;
			}
		}
		if (!quit) {
			result = responses;
		}
		return result;
	}
	private String collectInputFromInputNumber(int inputIndex, String[] responses, String cancel, String[] strings) {
		String raw = "";
		boolean redo = false;
		int inputsCounter = 0;
		int componentsCounter = 0;
		int stringsCounter = 0;
		Scanner scan = new Scanner(System.in);
		do {
			redo = false;
			inputsCounter = 0;
			componentsCounter = 0;
			stringsCounter = 0;
			System.out.println("\u001b[2J");
			while (inputsCounter <= inputIndex) {
				if (componentsCounter < this.components.length) {
					String toBePrinted = this.components[componentsCounter].toString();
					String replace = "{{{" + stringsCounter + "}}}";
					while (toBePrinted.contains(replace)) {
						toBePrinted = toBePrinted.replace(replace, strings[stringsCounter]);
						replace = "{{{" + (++stringsCounter) + "}}}";
					}
					System.out.print(this.components[componentsCounter]);
					if (this.components[componentsCounter].getClass() == DisplayInputComponent.class) {
						if (inputsCounter < inputIndex) {
							System.out.print(responses[inputsCounter]);
						}
						else {
							raw = scan.nextLine();
							if ((((DisplayInputComponent)this.components[componentsCounter]).getType() == InputType.OPTION && !raw.matches("^[1234567890" + cancel + "]+$"))
									|| (((DisplayInputComponent)this.components[componentsCounter]).getType() == InputType.OK && !raw.matches("^[" + cancel + "]+$"))) {
								redo = true;
							}
						}
						inputsCounter++;
					}
					System.out.println();
					componentsCounter++;
				} //else iterate infinitely
				//TODO: implement unlock
			}
		} while (redo);
		return raw;
	}
}
