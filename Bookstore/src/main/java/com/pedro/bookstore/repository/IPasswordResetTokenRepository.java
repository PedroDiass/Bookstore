package com.pedro.bookstore.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pedro.bookstore.model.User;
import com.pedro.bookstore.security.PasswordResetToken;

public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	
	PasswordResetToken findByToken(String token);
	
	PasswordResetToken findByUser(User user);
	
	Stream<PasswordResetToken> findByExpiryDateLessThan(Date expiryDate);
	
	@Modifying
	@Query("delete from PasswordResetToken prt where prt.expiryDate <= ?1")
	void deleteAllExpiredSince(Date date);
}
