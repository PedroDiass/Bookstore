package com.pedro.bookstore.service;

public interface IVerificationTokenService {
	
	void verifyUserEmail(String token);
	
}
