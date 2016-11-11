package com.happymart;

public class DisplayOptionComponent extends DisplayComponent {
	private String pretext;
	private String[] options;
	
	public DisplayOptionComponent (String pretext, String[] options) {
		this.pretext = pretext;
		this.options = options;
	}
	
	public String[] getOptions() {
		return this.options;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.pretext);
		for (int i = 0; i < this.options.length; i++) {
			builder.append("\n" + (i+1) + ". " + this.options[i]);
		}
		return builder.toString();
	}
}
