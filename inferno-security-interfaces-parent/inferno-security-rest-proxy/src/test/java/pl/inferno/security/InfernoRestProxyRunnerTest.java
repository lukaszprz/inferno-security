/**
 *
 */
package pl.inferno.security;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.proxy.service.UserProxyService;

/**
 * @author lukasz-adm
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfernoRestProxyRunnerTest {

	private Logger LOGGER = LoggerFactory.getLogger(InfernoRestProxyRunnerTest.class);

	@Autowired
	private WebApplicationContext ctx;

	@LocalServerPort
	private int serverPort;

	@Autowired
	private UserProxyService userProxyService;

	/**
	 * Test method for
	 * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
	 */
	@Test
	public final void testUserByName() {
		UserDTO user = userProxyService.getUserByUsername("inferno-admin");
		LOGGER.info("USER-ID : {}, USERNAME: {}", user.getId(), user.getUsername());
		Assert.assertNotNull(user);
	}

	/**
	 * Test method for
	 * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
	 */
	@Test
	public final void testUserById() {
		UserDTO user = userProxyService.getUserById(3L);
		LOGGER.info("USER-ID : {}, USERNAME: {}", user.getId(), user.getUsername());
		Assert.assertNotNull(user);
	}

	/**
	 * Test method for
	 * {@link pl.inferno.security.InfernoRestProxyRunner#main(java.lang.String[])}.
	 */
	@Test
	public final void testAllUsers() {
		List<UserDTO> users = userProxyService.getAllUsers();
		LOGGER.info("USERS : {}", users);
		Assert.assertNotNull(users);
	}

}
