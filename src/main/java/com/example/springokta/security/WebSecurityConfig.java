package com.example.springokta.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizacionFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JWTAutenticationFilter jwtAutenticationFilter = new JWTAutenticationFilter();
        jwtAutenticationFilter.setAuthenticationManager(authManager);
        jwtAutenticationFilter.setFilterProcessesUrl("/login");// VER SI LO PUEDO ENVIAR A LA VISTA QUE NO SEA LOCALHOST:8080/login

        return http
                .cors().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
//                .and()
//                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAutenticationFilter)
                .addFilterBefore(jwtAuthorizacionFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles()
//                .build());
//        return manager;
//    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] aar) {
//        System.out.println("Pass: "+new BCryptPasswordEncoder().encode("rsaldanar"));
//    }

}
