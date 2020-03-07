package com.pedro.bookstore.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro.bookstore.exceptions.ExceptionEmailAlreadyInUse;
import com.pedro.bookstore.model.User;
import com.pedro.bookstore.model.VerificationToken;
import com.pedro.bookstore.repository.IPasswordResetTokenRepository;
import com.pedro.bookstore.repository.IUserRepository;
import com.pedro.bookstore.repository.IVerificationTokenRepository;
import com.pedro.bookstore.security.PasswordResetToken;
import com.pedro.bookstore.security.Role;
import com.pedro.bookstore.security.UserRole;
import com.pedro.bookstore.service.IEmailSenderService;
import com.pedro.bookstore.service.IUserService;
import com.pedro.bookstore.utils.Constants;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IEmailSenderService emailSenderService;
	
	@Autowired
	private IVerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private IPasswordResetTokenRepository passwordResetTokenRepository; 
	
	@Override
	public void createAccount(User user) throws ExceptionEmailAlreadyInUse {
		UserRole userRole = new UserRole(user, createUserDefaultRole());
		
		Set<UserRole> userRoles = new HashSet<UserRole>(Arrays.asList(userRole));
		
		this.createAccount(user, userRoles);
	}
	
	@Override
	public void createAccount(User user, Set<UserRole> UserRoles) throws ExceptionEmailAlreadyInUse {
		if (! isEmailAlreadyInUse(user)) {
			user.setUserRoles(UserRoles);
			
			this.userRepository.save(user);
			
			this.sendVerificationEmail(user);
		} else {
			throw new ExceptionEmailAlreadyInUse();
		}
	}
	
	@Override
	public User enableUserAccount(String userEmail) {
		User user = userRepository.findByEmail(userEmail);
		user.setEnabled(true);
		userRepository.save(user);
		
		return user;
	}
	
	
	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}
	
	@Override
	public boolean isEmailAlreadyInUse(User user) {
		return userRepository.findByEmail(user.getEmail()) != null;
	}
	
	private void sendVerificationEmail(User user) {
		VerificationToken verificationToken = verificationTokenRepository.save(new VerificationToken(user));
        
        emailSenderService.sendEmail(
        		Constants.EMAIL_ADDRESS_DEFAULT_SENDER,
        		user.getEmail(),
        		Constants.ACCOUNT_VERIFICATION_SUBJECT,
        		Constants.ACCOUNT_VERIFICATION_TEXT + verificationToken.getToken());
	}
	
	private Role createUserDefaultRole() {
		Role role = new Role();
		role.setId(1);
		role.setName("DEFAULT_ROLE");
		
		return role;
	}
	
}






