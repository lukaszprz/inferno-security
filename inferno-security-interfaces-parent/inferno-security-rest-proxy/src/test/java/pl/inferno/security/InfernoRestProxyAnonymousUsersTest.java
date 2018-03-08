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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Test
    public void testWrongUserLogin() {
        UserDTO user = new UserDTO();
        user.setUsername("anon");
        user.setPassword("qwerty");
        ResponseEntity<String> tokenResponse = null;
        try {
            tokenResponse = userProxyService.login(user);
        } catch (HttpClientErrorException e) {
            LOGGER.error("Exception Response Body content is: {}", e.getResponseBodyAsString());
            tokenResponse = new ResponseEntity<String>(e.getResponseBodyAsString(), e.getResponseHeaders(), e.getStatusCode());
        }
        assertEquals("User should not be found.", HttpStatus.UNPROCESSABLE_ENTITY, tokenResponse.getStatusCode());
        assertFalse("Token response headers should not be empty.", tokenResponse.getHeaders().isEmpty());
        assertNull("Authentication token should be missing in headers.", tokenResponse.getHeaders().get(userProxyService.HEADER_X_AUTH_TOKEN));
        assertTrue("Content in response expected.", tokenResponse.hasBody());
        assertNotNull("Body in response expected.", tokenResponse.getBody());
        Map<String, Object> bodyResponse = new HashMap<>();
        try {
            bodyResponse = new ObjectMapper().readValue(tokenResponse.getBody(), HashMap.class);
        } catch (IOException e) {
            LOGGER.error("JSON parse error: {}", e.getMessage());
        }
        assertEquals("Invalid credentials message should be returned in body.", "Invalid Credentials", bodyResponse.get("message"));
        assertEquals("HTTP Status Code value should be the same in body.", HttpStatus.UNPROCESSABLE_ENTITY.value(), bodyResponse.get("status"));
        assertEquals("Expocted error should be the same in body.", HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), bodyResponse.get("error"));
    }

}
