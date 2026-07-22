package com.unicuritiba.barbearia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.antMatchers(
					"/",
					"/index",
					"/agendamento-cliente",
					"/sucesso-agendamento",
					"/login",
					"/css/**",
					"/js/**",
					"/img/**"
				).permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/valida-login")
				.usernameParameter("cpf")
				.passwordParameter("senha")
				.defaultSuccessUrl("/orderAdmin", true)
				.failureUrl("/login?error")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			);
		return http.build();
	}
}
