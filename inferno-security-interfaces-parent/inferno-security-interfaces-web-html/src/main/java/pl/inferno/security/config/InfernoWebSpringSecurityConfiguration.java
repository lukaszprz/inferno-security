/**
 * Project: inferno-security-interfaces-web-html
 * File: InfernoWebSpringSecurityConfiguration.java
 * Package: pl.inferno.security.config
 * Location:
 * 8 mar 2018 10:49:31 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import pl.inferno.security.proxy.errors.handlers.InfernoAccessDeniedExceptionHandler;
import pl.inferno.security.web.authentication.providers.InfernoProxyRestAuthenticationProvider;

/**
 * Class InfernoWebSpringSecurityConfiguration
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Configuration
// @EnableWebSecurity
public class InfernoWebSpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private InfernoProxyRestAuthenticationProvider authenticationProvider;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

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

    // @Override
    // public void configure(WebSecurity web) throws Exception {
    // web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**",
    // "/js/**", "/images/**", "/webjars/**");
    // }

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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new InfernoAccessDeniedExceptionHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
