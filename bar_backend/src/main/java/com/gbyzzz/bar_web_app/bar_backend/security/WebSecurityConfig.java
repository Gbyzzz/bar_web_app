package com.gbyzzz.bar_web_app.bar_backend.security;

import com.gbyzzz.bar_web_app.bar_backend.security.jwt.AuthTokenFilter;
import com.gbyzzz.bar_web_app.bar_backend.security.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class WebSecurityConfig {


	private final AuthenticationProvider authenticationProvider;
	private final AuthService authService;
	private final AuthTokenFilter authTokenFilter;

	public WebSecurityConfig(AuthenticationProvider authenticationProvider,
							 AuthService authService, AuthTokenFilter authTokenFilter) {
		this.authenticationProvider = authenticationProvider;
		this.authService = authService;
		this.authTokenFilter = authTokenFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((authorizeHttpRequests) ->
						authorizeHttpRequests.requestMatchers("/**")
								.permitAll())
				.sessionManagement((sessionManagement) ->
						sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.logout((logout) ->
						logout.logoutUrl("/logout")
								.addLogoutHandler(authService)
								.logoutSuccessHandler((request, response, authentication) ->
										SecurityContextHolder.clearContext()));
		return http.build();
	}

}
