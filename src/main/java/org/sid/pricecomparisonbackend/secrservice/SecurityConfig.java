package org.sid.pricecomparisonbackend.secrservice;

import org.sid.pricecomparisonbackend.secrservice.filters.JwtAuthenticationFilter;
import org.sid.pricecomparisonbackend.secrservice.filters.JwtAuthorizationFilter;
import org.sid.pricecomparisonbackend.secrservice.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsServiceImpl userDetailsService;
  private AuthEntryPointJwt unauthorizedHandler;

  public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler) {
    this.userDetailsService = userDetailsService;
    this.unauthorizedHandler = unauthorizedHandler;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
            .and()
            .csrf()
            .disable()
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests().antMatchers("/login/**","/signup/**",
                    "/login/",
                    "login",
                    "/refreshToken/**",
                    "/products/**",
                    "/categories/**").permitAll()
//    http.formLogin();
//    http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("USER");
            .anyRequest()
            .authenticated();
    http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
    http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
