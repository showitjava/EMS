package com.ems.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ems.security.JwtFilter;
import com.ems.services.CustomUserDetailsService;

@Configuration
public class SecurityConfig {
	
	
	private CustomUserDetailsService userDetailsService;
	private JwtFilter jwtFilter;
	
	public SecurityConfig (CustomUserDetailsService userDetailsService,JwtFilter jwtFilter)
	{
		this.userDetailsService = userDetailsService;
		this.jwtFilter = jwtFilter;
	}
	
	/*
	 * @Bean UserDetailsService CustomUserDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                				.requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/hello").permitAll()
                                .requestMatchers(HttpMethod.POST,"/employees/create").hasAnyRole("ADMIN")
                                .requestMatchers("/employees/**").hasAnyRole("ADMIN", "USERS")
                                .anyRequest().authenticated()

                );
        http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		//http.formLogin(withDefaults());
		http.httpBasic(withDefaults()
				);
		return http.build();
		
}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
		
		
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		
		return new BCryptPasswordEncoder();
	}
	
	

}
