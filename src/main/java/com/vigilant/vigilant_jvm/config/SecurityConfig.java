package com.vigilant.vigilant_jvm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer; // Added this import
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Allow the scanning engine to receive data publicly
                        .requestMatchers("/api/v1/sentinel/scan").permitAll()

                        // 2. Lock the Dashboard UI and the Data API
                        .requestMatchers("/", "/index.html", "/api/v1/sentinel/dashboard").authenticated()

                        // 3. Everything else requires login
                        .anyRequest().authenticated()
                )
                // Use the static import 'withDefaults' directly
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        @SuppressWarnings("deprecation")
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("sentinel_admin")
                .password("matters2026") // Keeping your secure password
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}