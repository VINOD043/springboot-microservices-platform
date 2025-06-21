package com.mycompany.auth.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Value("${spring.ldap.urls}")
    private String ldapUrl;

    @Value("${spring.ldap.base}")
    private String ldapBaseDn;

    @Value("${spring.ldap.username}")
    private String ldapAdminDn;

    @Value("${spring.ldap.password}")
    private String ldapAdminPassword;
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/login", "/auth/validate").permitAll()
            .anyRequest().authenticated()
        )
        .authenticationProvider(ldapAuthenticationProvider());

    return http.build();
}
    
    @Bean
    AuthenticationProvider ldapAuthenticationProvider() {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource());
        authenticator.setUserDnPatterns(new String[] { "cn={0},ou=people" });

        return new LdapAuthenticationProvider(authenticator);
    }

    @Bean
    DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(
            Collections.singletonList(ldapUrl), ldapBaseDn
        ) {{
            setUserDn(ldapAdminDn);
            setPassword(ldapAdminPassword);
            afterPropertiesSet(); // Required to initialize context source
        }};
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(ldapAuthenticationProvider());
        return builder.build();
    }
}
