/**
 * Project: inferno-security-rest-proxy
 * File: InfernoSuiteTest.java
 * Package: pl.inferno.security
 * Location:
 * 7 mar 2018 23:44:10 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Class InfernoSuiteTest
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@RunWith(Suite.class)
@SuiteClasses({ InfernoRestProxyRunnerTest.class, InfernoRestProxyAnonymousUsersTest.class })
public class InfernoTestSuite {

}
