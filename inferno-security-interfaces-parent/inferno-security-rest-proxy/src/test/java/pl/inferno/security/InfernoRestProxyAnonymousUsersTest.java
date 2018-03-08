/**
 * Project: inferno-security-rest-proxy
 * File: InfernoRestProxyAnonymousUsersTest.java
 * Package: pl.inferno.security
 * Location:
 * 8 mar 2018 01:02:05 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.proxy.service.UserProxyService;

/**
 * Class InfernoRestProxyAnonymousUsersTest
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfernoRestProxyAnonymousUsersTest {

    private Logger LOGGER = LoggerFactory.getLogger(InfernoRestProxyAnonymousUsersTest.class);

    @Autowired
    private UserProxyService userProxyService;

    @Rule
    public InfernoRestProxyTestRule infernoRule = new InfernoRestProxyTestRule();

    @Test(expected = HttpClientErrorException.class)
    public void testAllUsersThrowException() {
        List<UserDTO> users = userProxyService.getAllUsers(null);
        Assert.assertNull(users);
    }

    @Test(expected = HttpServerErrorException.class)
    public void testCurrentUserThrowException() {
        ResponseEntity<?> currentUser = userProxyService.getCurrentUser(null);
        assertNull("User response entity was not null.", currentUser);
    }

    @Test
    public void testAllUsersFailed() {
        List<UserDTO> users = null;
        try {
            users = userProxyService.getAllUsers(null);
        } catch (HttpClientErrorException e) {
            LOGGER.error("Reported exception: {}: {}", e.getStatusCode().getReasonPhrase(), e.getRawStatusCode());
            assertEquals("Expected HTTP status is different than expected.", HttpStatus.FORBIDDEN, e.getStatusCode());
        } finally {
            Assert.assertNull("User list was not null.", users);
        }
    }

    @Test
    public void testCurrentUserFailed() {
        ResponseEntity<?> currentUser = null;
        try {
            currentUser = userProxyService.getCurrentUser(null);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Reported exception: {}: {}", e.getStatusCode().getReasonPhrase(), e.getRawStatusCode());
            assertEquals("Expected HTTP status is different than expected.", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, e.getStatusCode());
        } finally {
            assertNull("User response entity was not null.", currentUser);
        }
    }

}
