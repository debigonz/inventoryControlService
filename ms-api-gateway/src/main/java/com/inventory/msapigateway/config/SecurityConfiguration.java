package com.inventory.msapigateway.config;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;

@AllArgsConstructor
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private ServerAuthenticationConverter authenticationConverter;
    private ReactiveAuthenticationManager manager;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository client) {
        http.oauth2Login(oauth2Login ->
                oauth2Login
                        .authenticationConverter(authenticationConverter)
                        .authenticationManager(manager));
        http.logout(logoutSpec -> logoutSpec.logoutSuccessHandler(
                new OidcClientInitiatedServerLogoutSuccessHandler(client)
        ));
        http.authorizeExchange(authorize ->
                authorize
                        .anyExchange()
                        .authenticated());
        http.cors(ServerHttpSecurity.CorsSpec::disable);
        return http.build();
    }
}
