package com.voting.exception;

@SuppressWarnings("serial")
public class VoteNotAllowedException extends RuntimeException{

	public VoteNotAllowedException(String msg) {
		super(msg);
	}

	
}
