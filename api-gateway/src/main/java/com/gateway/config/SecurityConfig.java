package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
          return http.csrf(csrf->csrf.disable())
                  .authorizeHttpRequests(auth->
                          auth.anyRequest().authenticated())
                  .oauth2ResourceServer(oauth2->
                          oauth2.jwt(Customizer.withDefaults()))
                  .build();
    }

}
