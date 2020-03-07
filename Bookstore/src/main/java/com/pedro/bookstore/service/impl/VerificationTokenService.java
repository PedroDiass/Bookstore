package com.pedro.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro.bookstore.model.VerificationToken;
import com.pedro.bookstore.repository.IVerificationTokenRepository;
import com.pedro.bookstore.service.IUserService;
import com.pedro.bookstore.service.IVerificationTokenService;

@Service
@Transactional
public class VerificationTokenService implements IVerificationTokenService {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IVerificationTokenRepository verificationTokenRepository;
	
	@Override
	public void verifyUserEmail(String token) {
		VerificationToken verificationToken = this.verificationTokenRepository.findByToken(token);
		
		String userEmail = verificationToken.getUser().getEmail();
		this.userService.enableUserAccount(userEmail);
	}

}
