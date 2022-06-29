package com.capg.hotelbookingmanagementsystem.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capg.hotelbookingmanagementsystem.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtil;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		 String username = null;
	     String jwtToken = null;

	        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	            jwtToken = requestTokenHeader.substring(7);
	            try {
	            	username = jwtUtil.extractUsername(jwtToken);
				} catch (ExpiredJwtException e) {
					e.printStackTrace();
					System.out.println("Jwt token has expired");
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error");
				}
	            
	        }if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

	            if (jwtUtil.validateToken(jwtToken, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }else {
	        	   System.out.println("Token is not valid");
	        }
	        filterChain.doFilter(request, response);
	    }
		
	}

