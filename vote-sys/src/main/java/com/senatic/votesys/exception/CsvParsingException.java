package com.senatic.votesys.exception;

import java.io.IOException;

public class CsvParsingException extends IOException {

	private static final long serialVersionUID = 1L;
	
	public CsvParsingException() {
		
	}
	
	public CsvParsingException(String message) {
		super(message);
	}
	
	public CsvParsingException(Throwable cause) {
		super(cause);
	}
	
	public CsvParsingException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
