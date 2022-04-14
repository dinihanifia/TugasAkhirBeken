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
	}
