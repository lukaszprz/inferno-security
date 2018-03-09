/**
 * Project: inferno-security-interfaces-web-db
 * File: InfernoWebSecurityConfiguration.java
 * Package: pl.inferno.security.configuration
 * Location:
 * 9 mar 2018 07:28:50 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import pl.inferno.security.errors.handler.InfernoAccessDeniedExceptionHandler;

/**
 * Class InfernoWebSecurityConfiguration
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Configuration
// @EnableWebSecurity
public class InfernoWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        // @formatter:on
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#authenticationManagerBean()
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @// @formatter:off
         auth.jdbcAuthentication()
            .usersByUsernameQuery(usersQuery)
            .authoritiesByUsernameQuery(rolesQuery)
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder);
     // @formatter:on
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new InfernoAccessDeniedExceptionHandler();
    }

}
