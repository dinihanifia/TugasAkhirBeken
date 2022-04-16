package com.bekennft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/signin")
	private String signinUser(Model model) {
		return "formsignin";
	}
	
	@GetMapping("/signup")
	private String signupUser(Model model) {
		return "formsignup";
	}
	

}
