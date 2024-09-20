package com.security.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

    @SneakyThrows
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests(
                        urlConfig -> urlConfig
                        .requestMatchers(
                                "/login",
                                "/users/registration")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.successForwardUrl("/users"));
        return http.build();
    }

    @SneakyThrows
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http,
                                                UserDetailsService userDetailsService,
                                                PasswordEncoder passwordEncode) {
        var managerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        managerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncode);

        return managerBuilder.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
