package com.abhi.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.login.config.MyUserDetailsService;
import com.abhi.login.model.AuthRequest;
import com.abhi.login.model.AuthResponse;
import com.abhi.login.util.JwtUtil;

@RestController
public class LoginController {
	
	@Autowired(required = true)
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService myUserDetailsService ;
	
	@RequestMapping("/dashboard")
	public String getdashboard() {
		return "List of dashboard";
	}
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenicate(@RequestBody AuthRequest authRequest) throws Exception {
		System.out.println("syso");
		try{
			authenticationManager.authenticate(	
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPass()));
		}
		catch (BadCredentialsException e) {
			throw new Exception("Bad creds");
		}
		
		UserDetails userDetails= myUserDetailsService.loadUserByUsername(authRequest.getUserName());
		
		String jwt = JwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthResponse(jwt));
			
	}
}
