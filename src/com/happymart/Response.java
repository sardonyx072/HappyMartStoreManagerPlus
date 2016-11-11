package com.happymart;

import java.io.Serializable;

public class Response<T> implements Serializable {
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
