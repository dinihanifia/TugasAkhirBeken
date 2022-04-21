package com.bekennft.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bekennft.model.UserModel;
import com.bekennft.repository.UserRepository;


@Service
public class JwtUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
}
