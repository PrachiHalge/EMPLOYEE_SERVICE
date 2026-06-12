package com.msedcl.main.employee.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// load bearer token
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			// Extract token and Remove "Bearer "
			String token = authHeader.substring(7);

			// Claim token
			String userName = jwtUtil.extractUserName(token);

			// Till this point my JWT Token authentication only
			// Settings for Spring Authentication
			// Letting spring know that user is already authenticated by JWT Token
			UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(userName,
					null, new ArrayList<>());

			// This tells spring,user is authenticated
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		}
		filterChain.doFilter(request, response);

	}

}
