package pl.inferno.security.interfaces.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Profile("it")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfernoRestInterfacesRunnerTest {

	// This will hold the port number the server was started on
	@Value("${local.server.port}")
	int port;

	final RestTemplate template = new RestTemplate();

	@Test
	public void contextLoads() {
	}

	// @Test
	// public void verifyUsers() throws Exception {
	// User user = template.getForObject("http://localhost:" + port +
	// "/api/user/name/inferno-admin", User.class);
	// Assert.assertNotNull(user);
	// }

}
