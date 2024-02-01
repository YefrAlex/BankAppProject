package de.telran_yefralex.BankAppProject.security.config;

import de.telran_yefralex.BankAppProject.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(
                        authz -> authz
                               .requestMatchers(
                                      "/auth/**",
                                       "/v2/api-docs/**",
                                        "/configuration/ui",
                                        "/swagger-resources/",
                                        "/configuration/security",
                                        "/webjars/",
                                       "/v3/api-docs/**",
                                       "/swagger-ui.html",
                                       "/api/v1/auth/",
                                       "/swagger-ui/**",
                                       "/product/all-active",
                                       "/product/all-active/{type}"
                              )
                                .permitAll()
                                .anyRequest().authenticated()
                ).addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
