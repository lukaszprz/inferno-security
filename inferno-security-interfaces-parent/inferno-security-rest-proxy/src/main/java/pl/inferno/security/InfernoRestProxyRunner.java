/**
 *
 */
package pl.inferno.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lukasz-adm
 *
 */
@SpringBootApplication
// @EnableJpaRepositories(basePackages = "pl.inferno.security.core.repository")
@EntityScan(basePackages = "pl.inferno.security.core.model")
@ComponentScan(basePackages = "pl.inferno.security")
public class InfernoRestProxyRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(InfernoRestProxyRunner.class, args);

	}

}
