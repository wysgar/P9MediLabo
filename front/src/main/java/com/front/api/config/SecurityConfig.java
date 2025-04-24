package com.front.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/", "/css/bootstrap.min.css").permitAll();
			auth.anyRequest().authenticated();
		})
		.formLogin(form -> form
				.loginPage("/login")
                .defaultSuccessUrl("/patient/list", true)
                .failureUrl("/login?error=true")
				.permitAll())
		.logout((logout) -> logout
				.logoutUrl("/app-logout").logoutSuccessUrl("/login"));
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(users()).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
			.username("user")
			.password("$2y$10$FrLkafXblC2uSOjzRODR4eYA5LDKLuGk7dHlBtqwXDHkSDCJkG5V2")
			.roles("USER")
			.build();
		UserDetails admin = User.builder()
			.username("admin")
			.password("$2y$10$FrLkafXblC2uSOjzRODR4eYA5LDKLuGk7dHlBtqwXDHkSDCJkG5V2")
			.roles("USER", "ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}
