package com.bekennft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
		@GetMapping("/about")
		private String about(Model model) {
			return "about";
		}
		
		@GetMapping("/explore.html")
		private String explore(Model model) {
			return "explore.html";
		}
		
		@GetMapping("/contact")
		private String contact(Model model) {
			return "contact";
		}
		
		@GetMapping("/creators")
		private String creators(Model model) {
			return "creators";
		}

		@GetMapping("/")
		private String index(Model model) {
			return "index";
		}

		@GetMapping("/index-user")
		private String indexUser(Model model) {
			return "index-user";
		}

		@GetMapping("/explore-user")
		private String exploreUser(Model model) {
			return "explore-user";
		}

		@GetMapping("/create-user")
		private String createUser(Model model) {
			return "create-user";
		}

		@GetMapping("/collections-user")
		private String collectionsUser(Model model) {
			return "collections-user";
		}

		@GetMapping("/profile-user")
	private String profileUser(Model model) {
		return "profile-user";
	}

}

