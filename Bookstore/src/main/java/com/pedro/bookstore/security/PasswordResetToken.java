package com.pedro.bookstore.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.pedro.bookstore.model.User;

@Entity
public class PasswordResetToken {

	private static final int EXPIRATION_IN_MINUTES = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private User user;
	
	@Column(name = "expiryDate", nullable = false)
	private Date expiryDate;

	public PasswordResetToken(String token, User user) {
		super();
		
		this.token = token;
		this.user = user;
		this.expiryDate = this.calculateExpiryDate();
	}
	
	private Date calculateExpiryDate() {
		Calendar calendar = Calendar.getInstance();
		Long currentTimeInMillis = new Date().getTime();
		calendar.setTimeInMillis(currentTimeInMillis);
		calendar.add(Calendar.MINUTE, EXPIRATION_IN_MINUTES);
		
		return calendar.getTime();
	}
	
	public void updateToken(String token) {
		this.token = token;
		this.expiryDate = this.calculateExpiryDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}















