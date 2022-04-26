package com.bekennft.controller;


import java.io.IOException;
import java.util.List;

import org.hibernate.annotations.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bekennft.model.ContactModel;
import com.bekennft.model.ProductModel;
import com.bekennft.model.UserCustomModel;
import com.bekennft.model.UserModel;
import com.bekennft.repository.ContactRepository;
import com.bekennft.repository.ProductRepository;
import com.bekennft.repository.UserRepository;
import com.bekennft.utility.FileUtility;


@Controller
public class WebController {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	ContactRepository contactRepo;
	
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
	@GetMapping("/contact2")
	private String contact2(Model model) {
		return "contact2";
	}
	@GetMapping("/contactform")
	private String contactform(Model model) {
		model.addAttribute("ContactModel", new ContactModel());
		return "contactform";
	}

	@PostMapping("/contactform")
	private String saveContact(@ModelAttribute ContactModel data) {
		contactRepo.save(data);
		return "redirect:/contactform";
	}
	
	@GetMapping("/user-detail")
	private String userdetail(Model model) {
		return "user-detail";
	}
	
	/*
	 * @GetMapping("/user-detail/info") private List<UserCustomModel>
	 * getDataByEmailAndFullNameAndUsername(@RequestParam(name="email")String email,
	 * 
	 * @RequestParam(name="fullName")String
	 * fullName, @RequestParam(name="username")String username){ return
	 * userRepo.getDataByEmailAndFullnameAndUsername(email, fullName, username); }
	 */
	
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
	
	@GetMapping("/creators-user")
	private String creatorsuser(Model model) {
		return "creators-user";
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
	
	@GetMapping("/profile")
	private String profileUser(Model model, @RequestParam String username) {
		model.addAttribute("UserModel", new UserModel());
		return "redirect:/profile?success " + userRepo.findByUsername(username);
	}
	
	
	
}