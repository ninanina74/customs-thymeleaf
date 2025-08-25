package com.example.customs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

  @Bean UserDetailsService userDetailsService(PasswordEncoder pe){
    InMemoryUserDetailsManager m = new InMemoryUserDetailsManager();
    m.createUser(User.withUsername("admin").password(pe.encode("admin123")).roles("ADMIN").build());
    m.createUser(User.withUsername("buyer").password(pe.encode("buyer123")).roles("BUYER").build());
    m.createUser(User.withUsername("broker").password(pe.encode("broker123")).roles("BROKER").build());
    m.createUser(User.withUsername("wh").password(pe.encode("wh123")).roles("WAREHOUSE").build());
    m.createUser(User.withUsername("acct").password(pe.encode("acct123")).roles("ACCOUNTING").build());
    return m;
  }

  @Bean SecurityFilterChain filter(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(reg -> reg
        .requestMatchers("/css/**","/js/**","/images/**").permitAll()
        .requestMatchers("/","/login").permitAll()
        .requestMatchers("/flow","/flow/**").authenticated()
        .anyRequest().authenticated())
      .formLogin(Customizer.withDefaults())
      .logout(Customizer.withDefaults())
      .csrf(Customizer.withDefaults());
    return http.build();
  }
}
