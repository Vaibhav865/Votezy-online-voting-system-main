package com.voting.exception;

@SuppressWarnings("serial")
public class DuplicateResourceException extends RuntimeException
{

	public DuplicateResourceException(String msg) {
		super(msg);
	}

}
