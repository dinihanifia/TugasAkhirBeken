package com.bekennft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.bekennft.service.JwtUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	
	@Autowired
	JwtRequestFilter jwtReqFilter;
	
	@Autowired
	JwtUserDetailService jwtUserDetailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(jwtUserDetailService);
		auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEnco());
	}
	
	@Bean
	public PasswordEncoder passwordEnco() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	http://localhost:8080/customer/login
//	http://localhost:8080/customer/
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/user/signin","/user/signup","/"
		,"/index-user","/explore-user","/create-user","/collections-user","/profile-user","/creators-user"
		,"/contactform","/user-detail"
		,"/assets/**","/**").permitAll() //** untuk mengakses endpoint bebasnya
		.anyRequest().authenticated().and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	
}
