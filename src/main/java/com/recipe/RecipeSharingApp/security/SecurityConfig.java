package com.recipe.RecipeSharingApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public final class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/index.html").permitAll()

//                        .requestMatchers("/swagger-resources/", "/webjars/")
//                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                                .anyRequest().authenticated()
                );


        return http.build();
    }
}
