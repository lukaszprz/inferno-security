/**
 *
 */
package pl.inferno.security;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.proxy.service.UserProxyService;

/**
 * @author lukasz-adm
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfernoRestProxyRunnerTest {

    private Logger LOGGER = LoggerFactory.getLogger(InfernoRestProxyRunnerTest.class);

    private static final String USERNAME = "inferno_test";

    @Autowired
    private UserProxyService userProxyService;

    @Test
    public void testUserApi_login() {
        UserDTO user = new UserDTO();
        user.setUsername(USERNAME);
        user.setPassword("test123");
        user.setActive(true);
        // HttpEntity<String> encryptedPassword =
        // userProxyService.encryptPassword(user.getPassword());
        // user.setPassword(encryptedPassword.getBody());
        LOGGER.debug("USER: {}", user);

        ResponseEntity<?> response = performLogin(user);

        LOGGER.debug("LOGGIN AS {}", user.getUsername());
        if ((response != null) && (response.getHeaders() != null) && !response.getHeaders().isEmpty()) {
            LOGGER.debug("RESPONSE: {}", response);
        } else {
            LOGGER.error("RESPONSE ERROR: {}", response);
        }
        assertNotNull("Zalogowano? ", response);
    }

    @Test
    public void testToken() {
        UserDTO user = new UserDTO();
        user.setUsername(USERNAME);
        user.setPassword("test123");
        user.setActive(true);
        HttpEntity<?> tokenRequest;
        ResponseEntity<?> loginRequest = null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(UserProxyService.HEADER_X_AUTH_TOKEN, user.getToken());
        HttpEntity<UserDTO> response = getToken(httpHeaders);
        user.setAuthenticated(true);
        LOGGER.debug("TOKEN RESPONSE: {}", response);
        org.springframework.util.Assert.hasText(response.getHeaders().entrySet().toArray().toString(), UserProxyService.HEADER_X_AUTH_TOKEN);
        if (!response.getHeaders().containsKey(UserProxyService.HEADER_X_AUTH_TOKEN)) {
            loginRequest = performLogin(user);
        }
        user.setToken(response.getHeaders().getFirst(UserProxyService.HEADER_X_AUTH_TOKEN));
        loginRequest.status(HttpStatus.CREATED).body(user);

    }

    /**
     * Test method for
     * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
     */
    // @Test
    public final void testUserByName() {
        UserDTO user = userProxyService.getUserByUsername(USERNAME);

        Assert.assertNotNull(user);
    }

    /**
     * Test method for
     * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
     */
    // @Test
    public final void testAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDTO> authorize = getToken(httpHeaders);
        List<UserDTO> users = userProxyService.getAllUsers();
        LOGGER.info("USERS : {}", users);
        Assert.assertNotNull(users);
    }

    private ResponseEntity<?> performLogin(UserDTO user) {
        ResponseEntity<?> response = userProxyService.login(user);
        return response;
    }

    private HttpEntity<UserDTO> getToken(HttpHeaders headers) {
        return userProxyService.getToken(headers);
    }

}
