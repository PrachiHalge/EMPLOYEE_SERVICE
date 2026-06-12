package com.msedcl.main.employee.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.msedcl.main.employee.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(requests -> requests
	            .anyRequest().authenticated()
	        )
	        .addFilterBefore(jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class);

	    http.formLogin(withDefaults());
	    http.httpBasic(withDefaults());

	    return http.build();
	}
}
