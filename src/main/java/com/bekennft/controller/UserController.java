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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bekennft.config.JwtTokenUtil;
import com.bekennft.model.UserModel;
import com.bekennft.repository.UserRepository;
import com.bekennft.service.JwtUserDetailService;




@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtUserDetailService jwtUserDetailService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@GetMapping("/signin")
	private String signinUser(Model model) {
		return "formsignin";
	}
	
	@GetMapping("/signup")
	private String signupUser(Model model) {
		return "formsignup";
	}
	
	@PostMapping("/signup")
	private ResponseEntity<String> saveUSer(@RequestBody UserModel user){
		user.setPassword(pEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Behasil di buat");
	}
	
	@PostMapping("/signin")
	private ResponseEntity<?> login(@RequestBody UserModel userModel) throws Exception{
		authenticate(userModel.getUsername(),userModel.getPassword());
		final UserDetails userDetails = jwtUserDetailService
				.loadUserByUsername(userModel.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}
	private void authenticate(String username, String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			//User disabled
		throw new Exception("USER_DISABED", e);	
		} catch (BadCredentialsException e) {
			//invalid credentials
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}

}