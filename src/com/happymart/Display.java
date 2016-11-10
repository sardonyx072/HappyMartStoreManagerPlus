package com.happymart;

public abstract class Display {
	private String display;
	private Object[] items;
	
	public Display (String display, Object... items) {
		this.display = display;
		this.items = items;
	}
}
