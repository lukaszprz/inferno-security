/**
 *
 */
package pl.inferno.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * WebMvcConfiguration class.
 * </p>
 *
 * @author lukasz-adm
 * @version $Id: $Id
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * <p>
	 * passwordEncoder.
	 * </p>
	 *
	 * @return a
	 *         {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
	 *         object.
	 */
	// @Bean
	// public BCryptPasswordEncoder passwordEncoder() {
	// BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	// return bCryptPasswordEncoder;
	// }

}
