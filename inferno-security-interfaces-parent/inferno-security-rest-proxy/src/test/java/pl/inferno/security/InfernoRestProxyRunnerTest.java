/**
 *
 */
package pl.inferno.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import pl.inferno.security.proxy.dto.LoginTemplate;
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

    private Map<String, UserDTO> users = new HashMap<>();

    @Rule
    public InfernoRestProxyTestRule infernoRule = new InfernoRestProxyTestRule();

    @Test
    public void testUserApi_login() {
        LoginTemplate user = createUserTemplate();

        LOGGER.debug("USER: {}", user);

        ResponseEntity<String> response = performLogin(user);
        assertNotNull("Login response is null. ", response);
        assertEquals("Login failed. Login has not been accepted.", HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull("No token returned.", response.getBody());
    }

    @Test
    public void testToken() {
        UserDTO user = null;
        LoginTemplate userTemplate = new LoginTemplate();
        ResponseEntity<String> loginRequest = null;
        String token = getToken(USERNAME);
        if (token != null) {
            user = users.get(USERNAME);
            loginRequest = new ResponseEntity<String>(user.getToken(), HttpStatus.ALREADY_REPORTED);
        } else if (users.isEmpty()) {
            userTemplate = createUserTemplate();
            user = new UserDTO(userTemplate.getUsername(), userTemplate.getPassword());
            loginRequest = performLogin(userTemplate);
            token = getToken(USERNAME);
        }

        ResponseEntity<?> response = userProxyService.getCurrentUser(token);
        UserDTO currentUser = (UserDTO) response.getBody();
        LOGGER.debug("CURR USER: {}", currentUser);
        assertNotNull("Current user response failed.", response);
        assertEquals("Not found.", HttpStatus.FOUND, response.getStatusCode());
        assertFalse("Not authorized.", response.getStatusCode().equals(HttpStatus.UNAUTHORIZED));
        assertEquals("Token missmatch.", loginRequest.getBody(), user.getToken());
        assertEquals("Username not the same.", user.getUsername(), currentUser.getUsername());
        assertEquals("Principal is different.", user.getPrincipal(), currentUser.getPrincipal());
        assertEquals("Current user token is different.", user.getToken(), currentUser.getToken());
    }

    /**
     * Test method for
     * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
     */
    @Test
    public final void testUserByName() {
        UserDTO user = userProxyService.getUserByUsername(USERNAME);

        Assert.assertNotNull(user);
    }

    /**
     * Test method for
     * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
     */
    @Test(expected = HttpClientErrorException.class)
    public final void testAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (!users.isEmpty()) {
            UserDTO loggedInUser = users.get(USERNAME);
            httpHeaders.add(UserProxyService.HEADER_X_AUTH_TOKEN, loggedInUser.getToken());
        } else {
            ResponseEntity<String> loginResponse = performLogin(createUserTemplate());
            httpHeaders.add(UserProxyService.HEADER_X_AUTH_TOKEN, loginResponse.getBody());
        }

        List<UserDTO> users = userProxyService.getAllUsers(httpHeaders.getFirst(UserProxyService.HEADER_X_AUTH_TOKEN));
        Assert.assertNotNull(users);
    }

    @Before
    public void before() {
        LoginTemplate userTemplate = createUserTemplate();
        if (users.isEmpty()) {
            performLogin(userTemplate);
        }
        LOGGER.debug("Authenticated users count: {}", users.size());
    }

    private ResponseEntity<String> performLogin(LoginTemplate user) {
        ResponseEntity<String> response = userProxyService.login(user);
        assertNotNull("Login response not null test failed.", response);

        if (response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
            LOGGER.debug("User zalogowany to: {}", response);

            if (!users.containsKey(user.getUsername())) {
                UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword());
                userDTO.setToken(response.getBody());
                users.put(USERNAME, userDTO);
            } else {
                UserDTO authenticatedUser = users.get(USERNAME);
                authenticatedUser.setToken(response.getBody());
            }
        }
        return response;
    }

    private String getToken(String username) {
        UserDTO user = users.get(username);
        if (user != null) {
            return user.getToken();
        }
        return null;
    }

    private LoginTemplate createUserTemplate() {
        LoginTemplate user = new LoginTemplate();
        user.setUsername(USERNAME);
        user.setPassword("test123");
        return user;
    }

}
