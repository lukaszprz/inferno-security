/**
 * Project: inferno-security-interfaces-rest
 * File: InfernoRestInterfaceSecurityConfiguration.java
 * Package: pl.inferno.security.interfaces.rest.configuration
 * Location:
 * 28 lis 2017 18:06:08 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;

import pl.inferno.security.core.service.UserService;
import pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService;

/**
 * Class InfernoRestInterfaceSecurityConfiguration
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class InfernoRestInterfaceSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfernoRestInterfaceSecurityConfiguration.class);

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private InfernoTokenAuthenticationService tokenAuthenticationService;

    /**
     *
     */
    public InfernoRestInterfaceSecurityConfiguration() {
        super(true);
    }

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
			.requestCache()
			.and()
//			.securityContext().securityContextRepository(new HttpSessionSecurityContextRepository())
//			.and()
//			.sessionManagement().enableSessionUrlRewriting(true)
//			.and()
//			.userDetailsService((UserDetailsService) userDetailsService)
//			.requestCache()
//			.and()
			.csrf().disable()
			.exceptionHandling().and()
			.anonymous().and()
			.servletApi().and()
			.headers()//.defaultsDisabled()
//			.xssProtection().disable()
			.cacheControl().and().addHeaderWriter(new CacheControlHeadersWriter()).and()
//			.addHeaderWriter(new StaticHeadersWriter(HeaderWriterFilter.ALREADY_FILTERED_SUFFIX, ""))
//			.referrerPolicy(ReferrerPolicy.ORIGIN_WHEN_CROSS_ORIGIN).and()
//			.contentTypeOptions().disable().and()
//			.frameOptions().sameOrigin().and()
			.authorizeRequests()

			//allow anonymous resource requests
			.antMatchers("/").permitAll()
			.antMatchers("/favicon.ico").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/encrypt**").permitAll()

			//allow anonymous POSTs to login
			.antMatchers(HttpMethod.POST, "/api/login").permitAll()
//			.antMatchers(HttpMethod.GET, "/api/login").permitAll()


			//allow anonymous GETs to API
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()

			//defined Admin only API area
			.antMatchers("/admin/**").hasRole("ADMIN")

			//all other request need to be authenticated
			.anyRequest().hasRole("USER").and()

			// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
			.addFilterBefore(new InfernoLoginFilter("/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

			// custom Token based authentication based on the header previously given to the client
			.addFilterBefore(new InfernoAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);


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

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userDetailsService).passwordEncoder(passwordEncoder());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#userDetailsService()
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return (UserDetailsService) userDetailsService;
        // return username -> {
        // User user = userDetailsService.getUserByUserName(username);
        //
        // if (userDetailsService.isUserExist(user)) {
        // User foundUser = new User();
        // foundUser.setAccountExpired(user.isAccountExpired());
        // foundUser.setAccountLocked(user.isAccountLocked());
        // foundUser.setCreatedBy(user.getCreatedBy());
        // foundUser.setCreatedDate(user.getCreatedDate());
        // foundUser.setCredentialsExpired(user.isCredentialsExpired());
        // foundUser.setEnabled(user.isEnabled());
        // foundUser.setExpires(user.getExpires());
        // foundUser.setId(user.getId());
        // foundUser.setLastModifiedBy(user.getLastModifiedBy());
        // foundUser.setLastModifiedDate(user.getLastModifiedDate());
        // foundUser.setNewPassword(user.getNewPassword());
        // foundUser.setPassword(user.getPassword());
        // foundUser.setPerson(user.getPerson());
        // List<String> authorities = new ArrayList<>();
        // for (UserRoles assignedRole : user.getRoles()) {
        // authorities.add(assignedRole.getAuthority());
        // }
        // foundUser.setRoles(user.getRoles());
        // foundUser.setUsername(user.getUsername());
        // return foundUser;
        // } else {
        // throw new UsernameNotFoundException("Could not find the user " + username);
        // }
        // };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
