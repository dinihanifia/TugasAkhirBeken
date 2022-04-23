package com.bekennft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bekennft.config.JwtTokenUtil;
import com.bekennft.model.UserModel;
import com.bekennft.repository.UserRepository;
import com.bekennft.service.JwtUserDetailService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtUserDetailService jwtUserDetailService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	PasswordEncoder passEncod;
	
	@GetMapping("/signin")
	private String signinUser(Model model) {
		model.addAttribute("UserModel", new UserModel());
		return "formsignin";
	}
	
	@GetMapping("/signup")
	private String signupUser(Model model) {
		model.addAttribute("UserModel", new UserModel());
		return "formsignup";
	}
	
	@PostMapping("/signup")
	private String saveUser(@ModelAttribute UserModel user){
		user.setPassword(passEncod.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/user/signup?success";
		
//		return ResponseEntity.status(HttpStatus.CREATED).body("Akun berhasil dibuat");
	}
	
	@PostMapping("/signin")
	private String signin(@ModelAttribute UserModel userModel, Model model) throws Exception {
		authenticate(userModel.getUsername(),userModel.getPassword());
		final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userModel.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		ResponseEntity.ok(token);
		return "redirect:/index-user?success" + userModel.getUsername();
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch(DisabledException e) {
//			user disabled
			throw new Exception("USER_DISABLED", e);
		} catch(BadCredentialsException e) {
//			invalid credentials
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	

}

