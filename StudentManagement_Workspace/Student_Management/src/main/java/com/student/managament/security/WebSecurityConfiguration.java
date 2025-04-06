package com.student.managament.security;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.student.managament.service.impl.AdminDetailServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .securityMatcher("/**") 
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers(HttpMethod.PUT, "/api/students/**").permitAll()
	        	.requestMatchers("/api/students/search/**").permitAll()
	        	.requestMatchers("/api/students/unenrolled/**").permitAll()
	            .requestMatchers("/api/students/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	        )
	        .httpBasic(withDefaults())
	        .csrf(csrf -> csrf.disable()); 

	    return http.build();
	}

    @Bean
    public UserDetailsService userDetailsService(AdminDetailServiceImpl adminDetailService) {
        return adminDetailService;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
