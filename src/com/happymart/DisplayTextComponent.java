package com.happymart;

public class DisplayTextComponent extends DisplayComponent {
	private String text;
	
	public DisplayTextComponent (String text) {
		this.text = text;
	}
	public String toString() {
		return this.text;
	}
}
