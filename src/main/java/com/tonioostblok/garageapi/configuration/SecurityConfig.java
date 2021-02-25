package com.tonioostblok.garageapi.configuration;

import com.tonioostblok.garageapi.filters.JwtRequestFilter;
import com.tonioostblok.garageapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().
                antMatchers("/customer/**").hasAnyRole("ADMIN", "FRONTOFFICE").
                antMatchers("/car/**").hasAnyRole("ADMIN", "FRONTOFFICE", "MECHANIC").
                antMatchers("/repair/**").hasAnyRole("ADMIN", "FRONTOFFICE", "MECHANIC", "CASHIER").
                antMatchers("/part/**").hasAnyRole("ADMIN", "BACKOFFICE", "MECHANIC").
                antMatchers("/action/**").hasAnyRole("ADMIN", "BACKOFFICE", "MECHANIC").
                antMatchers("/file/**").hasAnyRole("ADMIN", "FRONTOFFICE").
                antMatchers("/auth").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}