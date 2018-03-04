/**
 *
 */
package pl.inferno.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lukasz-adm
 *
 */
// @EnableEurekaClient
@SpringBootApplication
// @EnableJpaRepositories(basePackages = "pl.inferno.security.core.repository")
// @EntityScan(basePackages = "pl.inferno.security.core.model")
// @ComponentScan(basePackages = "pl.inferno.security")
public class InfernoRestInterfacesRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfernoRestInterfacesRunner.class);

	public static void main(String[] args) {
		LOGGER.debug("The Inferno Rest Interface service is running ............");
		SpringApplication.run(InfernoRestInterfacesRunner.class, args);
	}

}
