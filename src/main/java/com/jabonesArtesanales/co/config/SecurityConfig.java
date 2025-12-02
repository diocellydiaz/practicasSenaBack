package com.jabonesArtesanales.co.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // deja públicos los endpoints de tu API por ahora
                .requestMatchers("/api/productos/**").permitAll()
                // si tienes otros endpoints públicos, los agregas aquí
                .anyRequest().permitAll()
            );

        return http.build();
    }

}
