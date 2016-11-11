package com.happymart;

public class DisplayInputComponent extends DisplayComponent {
	private String prompt;
	private InputType type;
	private boolean canCancel;
	
	public DisplayInputComponent (String prompt, InputType type, boolean canCancel) {
		this.prompt = prompt;
		this.type = type;
		this.canCancel = canCancel;
	}

	public InputType getType() {
		return this.type;
	}
	public boolean canCancel() {
		return this.canCancel;
	}
	public String toString() {
		return this.prompt;
	}
}
