package com.assignment.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
		userDetailsManager.setUsersByUsernameQuery("select username, password, active from users where username = ?");
		userDetailsManager.setAuthoritiesByUsernameQuery("select username, role from users where username = ?");
		return userDetailsManager;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(
			configurer->configurer
			.requestMatchers("/dashboard").authenticated()
			.anyRequest().permitAll()
			).formLogin(
				form->form
				.loginPage("/login").loginProcessingUrl("/authenticateTheUser")
				.defaultSuccessUrl("/dashboard", true)
				.permitAll()
			).logout(
				logout->logout
				.logoutSuccessUrl("/login")
				.permitAll()
			).exceptionHandling(
				configurer->configurer
				.accessDeniedPage("/error404")
			);
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
}
