/**
 * Project: inferno-security-rest-proxy
 * File: InfernoRestProxyTestRule.java
 * Package: pl.inferno.security
 * Location:
 * 7 mar 2018 23:15:23 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class InfernoRestProxyTestRule
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class InfernoRestProxyTestRule implements TestRule {

    private Statement base;

    private Description description;

    /*
     * (non-Javadoc)
     * @see org.junit.rules.TestRule#apply(org.junit.runners.model.Statement,
     * org.junit.runner.Description)
     */
    @Override
    public Statement apply(Statement base, Description description) {
        this.base = base;
        this.description = description;
        return new InfernoTestStatement(base);
    }

    public class InfernoTestStatement extends Statement {

        private final Logger LOGGER = LoggerFactory.getLogger(InfernoTestStatement.class);

        private final Statement base;

        /**
         * @param base
         */
        public InfernoTestStatement(Statement base) {
            this.base = base;
        }

        /*
         * (non-Javadoc)
         * @see org.junit.runners.model.Statement#evaluate()
         */
        @Override
        public void evaluate() throws Throwable {
            LOGGER.info("========= Running {}... =========", description.getMethodName());
            try {
                base.evaluate();
            } finally {
                LOGGER.info("-------- {} finished. --------", description.getMethodName());
            }
        }

    }

}
