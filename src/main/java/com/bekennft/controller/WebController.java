package com.bekennft.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bekennft.model.ProductModel;
import com.bekennft.repository.ProductRepository;
import com.bekennft.utility.FileUtility;


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
//		productRepo.findAll()
		model.addAttribute("productModel",productRepo.findAll());
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
	private String saveCreate(@ModelAttribute ProductModel data,
			@RequestParam(value="file")MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "create-images";
		
//		cara membaca image yang pertama data.setGambar("/"+uploadDir+"/"+fileName);

		data.setImages(fileName);
		FileUtility.simpanFile(uploadDir, fileName, file);
		productRepo.save(data);
		return "redirect:/explore-user";
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