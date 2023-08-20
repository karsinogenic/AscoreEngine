package com.example.BankMega.Ascore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         * Below is the custom security configurations
         */

        http.authorizeHttpRequests()
                .requestMatchers("/", "/ascore_calculator", "/ascore_management", "/ascore_management/**")
                .authenticated()
                .requestMatchers("/assets/**", "/media/**", "/js/**", "/image/**", "/plugins/**", "/sass/**", "/login1")
                .permitAll()
                .and().formLogin().loginPage("/login1").loginProcessingUrl("/login")
                .and().httpBasic();

        // http.authorizeHttpRequests()
        // .requestMatchers(HttpMethod.POST, "/api/score").authenticated()
        // .and()
        // .oauth2Client();

        http.authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()
                .and()
                .cors().and().csrf().disable();

        return http.build();

        // http.authorizeHttpRequests().anyRequest().denyAll()
        // .and().formLogin()
        // .and().httpBasic();
        // return http.build();

        // http.authorizeHttpRequests().anyRequest().permitAll()
        // .and().formLogin()
        // .and().httpBasic();
        // return http.build();

    }

}
