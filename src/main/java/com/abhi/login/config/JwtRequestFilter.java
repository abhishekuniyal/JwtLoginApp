package com.abhi.login.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Chain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.abhi.login.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String authorizationHeader = request.getHeader("Authorization");

		String userName = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(jwt);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(userName);
			
			if(jwtUtil.validateToken(jwt, userDetails)) {
				
				//everything is fine so use Spring classes to add the request
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
						= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//To set context once request is authenticated
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}	
			
		}
		filterChain.doFilter(request, response);

	}

}
