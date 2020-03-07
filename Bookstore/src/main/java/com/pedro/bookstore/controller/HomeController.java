package com.pedro.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pedro.bookstore.model.User;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/signIn")
	public String signIn() {
		return "signIn.html";
	}
	
	
	@GetMapping("/signUp")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		
		return "signUp.html";
	}
	
}
