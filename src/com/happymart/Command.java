package com.happymart;

public class Command<T> {
	private CommandType type;
	private T content;
	
	public Command (CommandType type, T content) {
		this.type = type;
		this.content = content;
	}
	public CommandType getType() {
		return this.type;
	}
	public T getContent() {
		return this.content;
	}
}
