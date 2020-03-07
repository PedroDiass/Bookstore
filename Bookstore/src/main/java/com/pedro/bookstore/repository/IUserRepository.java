package com.pedro.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pedro.bookstore.model.User;

public interface IUserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
}
