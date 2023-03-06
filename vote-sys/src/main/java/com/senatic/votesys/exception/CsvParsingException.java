package com.senatic.votesys.exception;

import java.io.IOException;

public class CsvParsingException extends IOException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CsvParsingException() {
		
	}
	
	public CsvParsingException(String message) {
		this.message = message;
	}
	
	public CsvParsingException(Throwable cause) {
		super(cause);
	}
	
	public CsvParsingException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
