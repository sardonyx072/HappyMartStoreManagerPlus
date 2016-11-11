package com.happymart;

import java.util.Scanner;

public enum DisplayState {
	TERMINAL_SETUP(
			new DisplayOptionComponent(	"Set up this terminal as:",new String[]{"Store server","Access terminal","Register"}),
			new DisplayInputComponent(	"Choice: ",InputType.OPTION,true)
			),
	OPEN_SESSION(
			new DisplayTextComponent(	"Welcome to Happy Mart Store Manager Plus!"),
			new DisplayTextComponent(	"Please sign in using your employee credentials."),
			new DisplayInputComponent(	"Username: ",InputType.TEXT,true),
			new DisplayInputComponent(	"Password: ",InputType.TEXT,true)
			),
	CLOSE_SESSION(
			new DisplayInputComponent(	"Really close session?",InputType.OK,true)
			),
	HOME_SCREEN(
			new DisplayOptionComponent(	"What would you like to do, {{{0}}}?",new String[]{}),
			new DisplayInputComponent(	"Choice: ",InputType.OPTION,true)
			);
	
	private DisplayComponent[] components;
	
	private DisplayState (DisplayComponent... components) {
		this.components = components;
	}
	public Object showAndRequestInput(String...strings) {
		int input = 0;
		int i = 0;
		String cancel = "`";
		String raw;
		Object result = new Boolean(false);
		boolean exit = false;
		Scanner scan = new Scanner(System.in);
		while (!exit && (raw = scan.nextLine()) != null) {
			if (input == 0 && raw.equals(cancel)) {
				exit = true;
			}
			else {
				if (this.components[i].getClass() == DisplayTextComponent.class) {
					
				}
			}
		}
		return result;
	}
}
