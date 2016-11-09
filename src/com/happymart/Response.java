package com.happymart;

public class Response<T> {
	private ResponseType type;
	private T content;
	
	public Response (ResponseType type, T content) {
		this.type = type;
		this.content = content;
	}
	public ResponseType getType() {
		return this.type;
	}
	public T getContent() {
		return this.content;
	}
}
