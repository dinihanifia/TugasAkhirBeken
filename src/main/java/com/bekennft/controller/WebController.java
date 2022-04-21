package com.bekennft.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bekennft.model.ProductModel;
import com.bekennft.repository.ProductRepository;


@Controller
public class WebController {

	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("/")
	private String index(Model model) {
		return "index";
	}
	
	@GetMapping("/about")
	private String about(Model model) {
		return "about";
	}
	
	@GetMapping("/explore")
	private String explore(Model model) {
		return "explore";
	}
	
	@GetMapping("/contact")
	private String contact(Model model) {
		return "contact";
	}
	
	@GetMapping("/creators")
	private String creators(Model model) {
		return "creators";
	}
	
	@GetMapping("/index-user")
	private String indexUser(Model model) {
		return "index-user";
	}
	
	@GetMapping("/explore-user")
	private String exploreUser(Model model) {
		return "explore-user";
	}
	
	@GetMapping("/create")
	private String create(Model model) {
		return "create";
	}
	
	@GetMapping("/create-user")
	private String createUser(Model model) {
		model.addAttribute("ProductModel", new ProductModel());
		return "create-user";
	}
	
	@PostMapping("/create-user")
	private String createImg(@ModelAttribute ProductModel product) {
		productRepo.save(product);
		return "redirect:/create-user";
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
