package com.pedro.bookstore.service;

public interface IEmailSenderService {

	public void sendEmail(String from, String to, String subject, String text);
	
}
