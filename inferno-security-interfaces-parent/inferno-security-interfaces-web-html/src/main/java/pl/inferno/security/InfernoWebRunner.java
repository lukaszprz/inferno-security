/**
 *
 */
package pl.inferno.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author lukasz-adm
 */
// @EnableEurekaClient
@SpringBootApplication
public class InfernoWebRunner extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfernoWebRunner.class);

    /*
     * (non-Javadoc)
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
        LOGGER.debug("The Inferno Web Interface service is running ...........");
        SpringApplication.run(InfernoWebRunner.class, args);

    }

}
