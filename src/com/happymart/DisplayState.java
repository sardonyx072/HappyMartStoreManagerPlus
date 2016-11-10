package com.happymart;

public enum DisplayState {
	/*
	TERMINAL_SETUP(
						"Set up this terminal as:"
			+ "\n" + 	" 1. Store Server"
			+ "\n" + 	" 2. Access Terminal"
			+ "\n" + 	" 3. Register"
			+ "<<<GET>>>"
			,
			InputType.CANCEL,InputType.OPTION
			),
	OPEN_SESSION(
						"Welcome to Happy Mart Store Manager Plus!"
			+ "\n" + 	"Please sign in with your employee credentials:"
			+ "\n" + 	"Username: "
			+ "<<<WAIT>>>"
			+ "\n" + 	"Password: "
			,
			InputType.CANCEL,InputType.TEXT,InputType.TEXT
			);
	
	private String display;
	private InputType[] inputTypes;
	
	private DisplayState (String display, InputType... inputTypes) {
		this.display = display;
		this.inputTypes = inputTypes;
	}
	*/
	TERMINAL_SETUP(
			new DisplayInputComponent(				"Set up this terminal as:"
										+ "\n\t" + 	"1. Store server"
										+ "\n\t" + 	"2. Access terminal"
										+ "\n\t" + 	"3. Register",
										InputType.OPTION,true)
			);
	
	private DisplayComponent[] components;
	
	private DisplayState (DisplayComponent... components) {
		this.components = components;
	}
}
