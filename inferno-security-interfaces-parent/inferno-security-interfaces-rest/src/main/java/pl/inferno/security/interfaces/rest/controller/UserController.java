package pl.inferno.security.interfaces.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.interfaces.rest.utils.CustomErrorType;
import pl.inferno.security.interfaces.rest.utils.UserAuthentication;

/**
 *
 */

/**
 * @author lukasz-adm
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/user/name/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
        LOGGER.info("Fetching User with username {}", username);
        User user = userService.getUserByUserName(username);
        if (user == null) {
            LOGGER.error("User with username {} not found.", username);
            return new ResponseEntity<>(new CustomErrorType("User with username " + username + " not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        LOGGER.info("Fetching User with id {}", id);
        User user = userService.findById(id);
        if (user == null) {
            LOGGER.error("User with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("User with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        } else {
            userService.deleteUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

    }

    // @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userService.getUserByUserName(user.getUsername()) != null) {
            throw new RuntimeException("Username already exist.");
        }
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/current", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            LOGGER.debug("CONTROLLER getCurrent(), authentication param: {}", authentication);
            return new ResponseEntity<>(((UserAuthentication) authentication).getDetails(), HttpStatus.FOUND);
        } else {
            User anonymousUser = new User();
            return new ResponseEntity<>(anonymousUser, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

    }

    @RequestMapping(value = "/users/current", method = RequestMethod.PATCH)
    public ResponseEntity<String> changePassword(@RequestBody final User user) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User currentUser = userService.getUserByUserName(authentication.getName());

        if ((user.getNewPassword() == null) || (user.getNewPassword().length() < 4)) {
            return new ResponseEntity<String>("New password to short.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(user.getPassword(), currentUser.getPassword())) {
            return new ResponseEntity<String>("Old password mismatch.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        currentUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
        userService.saveUser(currentUser);
        return new ResponseEntity<String>("Password changed.", HttpStatus.OK);
    }

    @RequestMapping(value = "/encrypt", method = RequestMethod.GET)
    public ResponseEntity<String> encryptPassword(@RequestParam(name = "rawPassword") String rawPassword) {

        return new ResponseEntity<String>(passwordEncoder.encode(rawPassword), HttpStatus.OK);
    }

}
