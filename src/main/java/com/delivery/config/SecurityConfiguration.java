package com.delivery.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth)
            throws Exception {


        String fetchUsersQuery = "select email, password, enabled"
                + " from users"
                + " where email = ?";

        String fetchRolesQuery = "select email, role"
                + " from users"
                + " where email = ?";
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(fetchUsersQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
