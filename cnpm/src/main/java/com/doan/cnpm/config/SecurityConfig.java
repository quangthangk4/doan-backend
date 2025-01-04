package com.doan.cnpm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] GLOBAL_ENDPOINTS = {
            "/global/**"
    };

    private final String[] ADMIN_ENDPOINTS = {
            "/admin/**"
    };

    private final String[] CUSTOMER_ENDPOINTS = {
            "/user/**"
    };

    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Enable CORS
        httpSecurity
                // Configure authorization rules
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Public endpoints (accessible to everyone)
                        .requestMatchers("/global/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("CUSTOMER")
//                        .requestMatchers(HttpMethod.POST, GLOBAL_ENDPOINTS).permitAll()
//                        .requestMatchers(HttpMethod.GET, GLOBAL_ENDPOINTS).permitAll()
//
//                        // Admin-specific endpoints (requires ADMIN role)
//                        .requestMatchers(HttpMethod.GET, ADMIN_ENDPOINTS).hasRole("ADMIN")
//
//                        // Customer-specific endpoints (requires CUSTOMER role)
//                        .requestMatchers(HttpMethod.GET, CUSTOMER_ENDPOINTS).hasRole("CUSTOMER")
//                        .requestMatchers(HttpMethod.POST, CUSTOMER_ENDPOINTS).hasRole("CUSTOMER")
//                        .requestMatchers(HttpMethod.PUT, CUSTOMER_ENDPOINTS).hasRole("CUSTOMER")
//                        .requestMatchers(HttpMethod.DELETE, CUSTOMER_ENDPOINTS).hasRole("CUSTOMER")

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )

                // Disable CSRF (not recommended for production unless justified)
                .csrf(AbstractHttpConfigurer::disable)

                // Configure OAuth2 Resource Server for JWT-based authentication
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .decoder(jwtDecoder())  // Custom JWT decoder
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // Convert JWT claims to GrantedAuthorities
                        )
                );

        // Build the SecurityFilterChain
        return httpSecurity.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Tên của claim chứa quyền hạn (ví dụ: "roles" hoặc "scope")
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        // Thêm tiền tố "ROLE_" nếu cần
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return authenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}