package com.pedro.bookstore.exceptions;

public class ExceptionEmailAlreadyInUse extends Exception {

	private static final long serialVersionUID = 1918626184959392585L;

	public ExceptionEmailAlreadyInUse() {
		super("emailAlreadyInUse");
	}
}
