package com.example.managementstore.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.managementstore.auth.Permission.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] AUTH_ALLOW_LIST = {
            "/auth/**",
            "/api/v1/auth/**",
            "/api/v1/search/**",
            "/v2/api-docs",
            "/v3/api-docs/",
            "/v3/api-docs/**",
            "/h2-console/**",
            "/h2-console/",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/webjars/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(AUTH_ALLOW_LIST)
                                .permitAll()
                                .requestMatchers("/api/v1/management/**").hasAnyAuthority(STORE_CREATE.getPermission(), STORE_UPDATE.getPermission(), STORE_DELETE.getPermission(), USER_MANAGE.getPermission(), ITEM_MANAGE.getPermission(), PURCHASE_ORDER_CREATE.getPermission())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
