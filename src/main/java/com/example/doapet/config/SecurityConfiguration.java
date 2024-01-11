package com.example.doapet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf().disable()
            .authorizeRequests()
              .requestMatchers(HttpMethod.POST  , "/auth/login").permitAll()
              .requestMatchers(HttpMethod.POST  , "/auth/register").permitAll()
              .requestMatchers(HttpMethod.GET   , "/usuario/").authenticated()
              .requestMatchers(HttpMethod.GET   , "/usuario/**").authenticated()
              .requestMatchers(HttpMethod.PATCH , "/usuario/**").authenticated()
              .requestMatchers(HttpMethod.PATCH , "/usuario/change-password/**").authenticated()
              .requestMatchers(HttpMethod.POST  , "/oferta-adocao/cadastrar").authenticated()
              .requestMatchers(HttpMethod.GET   , "/oferta-adocao/").authenticated()
              .requestMatchers(HttpMethod.GET   , "/oferta-adocao/**").authenticated()
              .requestMatchers(HttpMethod.GET   , "/oferta-adocao/byAdoptionRequest/**").authenticated()
              .requestMatchers(HttpMethod.POST  , "/solicitacao-adocao/**").authenticated()
              .requestMatchers(HttpMethod.GET   , "/solicitacao-adocao/**").authenticated()
              .requestMatchers(HttpMethod.GET   , "/solicitacao-adocao/pending/**").authenticated()
              .requestMatchers(HttpMethod.PATCH , "/solicitacao-adocao/accept/**").authenticated()
              .requestMatchers(HttpMethod.PATCH , "/solicitacao-adocao/reject/**").authenticated()
              .requestMatchers(HttpMethod.PATCH , "/solicitacao-adocao/byUser/**").authenticated()
              .requestMatchers(HttpMethod.POST  , "/evento/").authenticated()
              .requestMatchers(HttpMethod.DELETE, "/evento/**").authenticated()
              .requestMatchers(HttpMethod.GET   , "/evento/**").authenticated()
              .anyRequest().denyAll()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .cors(Customizer.withDefaults())
            .build();
  }
}
