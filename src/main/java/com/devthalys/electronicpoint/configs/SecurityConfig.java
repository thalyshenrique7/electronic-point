package com.devthalys.electronicpoint.configs;

import com.devthalys.electronicpoint.jwt.security.JwtAuthFilter;
import com.devthalys.electronicpoint.jwt.security.JwtService;
import com.devthalys.electronicpoint.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users/**")
                    .permitAll()
                .antMatchers("/api/users/auth/**")
                    .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/employee/**")
                    .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/employee/**")
                    .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE,"/api/employee/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/employee/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users/workedhours/**")
                    .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
