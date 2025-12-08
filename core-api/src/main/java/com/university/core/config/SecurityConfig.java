package com.university.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        configurer -> configurer

                                //registration
                                .requestMatchers("/showRegisterPage", "/save").permitAll()
                                .requestMatchers("/css/**", "/images/**").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/instructor/**").hasAnyRole("ADMIN", "INSTRUCTOR")
                                .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT")

                                .anyRequest().authenticated()
                )

                .formLogin(form ->
                        form
                                //.loginPage("/showMyLoginPage")
                                //.loginProcessingUrl("/authenticateTheUser")
                                .usernameParameter("email")
                                .permitAll()
                        )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();

    }
}
