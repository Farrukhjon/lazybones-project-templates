package org.farrukh.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                  .antMatchers("/*", "/home", "/api/sign_up", "/api/sign_in", "/login", "/logout", "/about").permitAll()
                  .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                  .antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                  .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                  .anyRequest().authenticated()
                .and()
                .httpBasic();
        // @formatter:on
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.userDetailsService(userDetailsService);
        // @formatter:on
    }

}
