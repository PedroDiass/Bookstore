package com.pedro.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pedro.bookstore.model.VerificationToken;

public interface IVerificationTokenRepository extends CrudRepository<VerificationToken, String> {
	
	    VerificationToken findByToken(String Token);
	    
}
