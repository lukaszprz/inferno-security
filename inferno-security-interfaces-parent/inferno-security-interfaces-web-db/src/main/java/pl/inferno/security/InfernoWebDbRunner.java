/**
 * Project: inferno-security-interfaces-web-db
 * File: InfernoWebDbRunner.java
 * Package: pl.inferno.security
 * Location:
 * 9 mar 2018 07:24:07 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Class InfernoWebDbRunner
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@SpringBootApplication
public class InfernoWebDbRunner extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfernoWebDbRunner.class);

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.boot.web.support.SpringBootServletInitializer#configure(
     * org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(InfernoWebDbRunner.class);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("The Inferno Web DB Interface service is running ...........");
        SpringApplication.run(InfernoWebDbRunner.class, args);

    }

}
