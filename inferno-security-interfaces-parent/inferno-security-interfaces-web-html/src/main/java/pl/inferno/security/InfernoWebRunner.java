/**
 *
 */
package pl.inferno.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author lukasz-adm
 *
 */
@SpringBootApplication
public class InfernoWebRunner extends SpringBootServletInitializer {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.boot.web.support.SpringBootServletInitializer#configure(
	 * org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(InfernoWebRunner.class);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(InfernoWebRunner.class, args);

	}

}
