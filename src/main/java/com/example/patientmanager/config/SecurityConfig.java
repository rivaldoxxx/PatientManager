package com.example.patientmanager.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()  // Pozwól na dostęp do H2
                .anyRequest().authenticated()               // Wymagaj autoryzacji dla innych zasobów
                .and()
                .csrf().disable()  // Wyłącz CSRF dla konsoli H2
                .headers().frameOptions().disable();  // Wyłącz zabezpieczenia nagłówków dla konsoli H2
    }
}
