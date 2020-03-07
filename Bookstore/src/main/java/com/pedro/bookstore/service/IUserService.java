package com.pedro.bookstore.service;

import java.util.Set;

import com.pedro.bookstore.exceptions.ExceptionEmailAlreadyInUse;
import com.pedro.bookstore.model.User;
import com.pedro.bookstore.security.PasswordResetToken;
import com.pedro.bookstore.security.UserRole;

public interface IUserService {
	void createAccount(User user) throws ExceptionEmailAlreadyInUse;
	
	void createAccount(User user, Set<UserRole> userRoles) throws ExceptionEmailAlreadyInUse;
	
	boolean isEmailAlreadyInUse(User user);
	
	PasswordResetToken getPasswordResetToken(String token);
	
	void createPasswordResetTokenForUser(User user, String token);
	
	User enableUserAccount(String userEmail);
}
