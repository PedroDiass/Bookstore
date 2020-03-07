package com.pedro.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pedro.bookstore.exceptions.ExceptionEmailAlreadyInUse;
import com.pedro.bookstore.model.User;
import com.pedro.bookstore.service.IUserService;
import com.pedro.bookstore.service.IVerificationTokenService;

@Controller
public class UserAccountController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IVerificationTokenService verificationTokenService;
	
	@PostMapping("/signUp")
	public String signUp(Model model, User user) {
		try {
			userService.createAccount(user);
			
			return "home.html";
		} catch(ExceptionEmailAlreadyInUse eEmailInUse) {
			model.addAttribute("emailAlreadyInUse", true);
			
			return "signUp.html";
		}
	}
	
    @GetMapping("/verifyAccount")
    public String verifyUserEmail(Model model, @RequestParam("token") String token) {
    	this.verificationTokenService.verifyUserEmail(token);
    	
    	model.addAttribute("message", "Account Successfully Verified!!");
    	
    	return "home.html";
    }
	
}




