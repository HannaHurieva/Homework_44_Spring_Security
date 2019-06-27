package com.alevel.homework44.config;

import com.alevel.homework44.security.jwt.JwtConfigurer;
import com.alevel.homework44.security.jwt.JwtTokenProvider;
import com.alevel.homework44.service.impl.UserServiceImpl;
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

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String REGISTRATION_ENDPOINT = "/registration";
    private static final String LOGIN_ENDPOINT = "/login";

    @Autowired
    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               // .httpBasic().disable()
               // .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/", REGISTRATION_ENDPOINT).permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage(LOGIN_ENDPOINT)
                .permitAll()

                .and()
                .logout()
                .permitAll()

                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
