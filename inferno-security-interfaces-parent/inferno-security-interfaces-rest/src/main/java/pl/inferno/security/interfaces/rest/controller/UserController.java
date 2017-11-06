package pl.inferno.security.interfaces.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.interfaces.rest.utils.CustomErrorType;

/**
 *
 */

/**
 * @author lukasz-adm
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/name/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> findUser(@PathVariable("username") String username) {
		LOGGER.info("Fetching User with username {}", username);
		User user = userService.getUserByUserName(username);
		if (user == null) {
			LOGGER.error("User with username {} not found.", username);
			return new ResponseEntity<>(new CustomErrorType("User with username " + username + " not found."),
			        HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
		LOGGER.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			LOGGER.error("User with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("User with id " + id + " not found."),
			        HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		LOGGER.info("Creating user : {}", user);
		if (userService.isUserExist(user)) {
			LOGGER.error("Unable to create. A User with username {} already exist", user.getUsername());
			return new ResponseEntity<>(
			        new CustomErrorType(
			                "Unable to create. A User with username " + user.getUsername() + " already exist"),
			        HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}
