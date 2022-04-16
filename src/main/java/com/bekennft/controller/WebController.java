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
	}
