package com.vfoexchange.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/*
Configuring spring oauth2 security
 */
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ServerSecurityConfig.class)
public class OAuth2Security extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthConfig config;

    @Autowired
    public OAuth2Security(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /*
    Method to enable gratnt type as password for authentication and setting token validity
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(config.getClientId()).secret(config.getClientSecret()).authorizedGrantTypes("password")
                .scopes("read", "write").accessTokenValiditySeconds(config.getAuthTokenValidity());
    }

    /*
    Method to override the authentication API path and including jwt token in spring security token store
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.pathMapping("/oauth/token", config.getAuthPath());
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    /*
    Creating jwt token using .jks file in resources and keystore password
     */
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"),
                config.getKeyStorePassword().toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

}
