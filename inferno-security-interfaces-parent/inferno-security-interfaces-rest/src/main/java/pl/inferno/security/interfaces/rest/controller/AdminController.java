/**
 * Project: inferno-security-interfaces-rest
 * File: AdminController.java
 * Package: pl.inferno.security.interfaces.rest.controller
 * Location:
 * 28 lis 2017 12:42:40 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.service.RoleService;
import pl.inferno.security.core.service.UserService;

/**
 * Class AdminController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@RestController
@RequestMapping(value = "/admin/api/")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/{user}/grant/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> grantRole(@PathVariable User user, @PathVariable String role) {
        if (user == null) {
            return new ResponseEntity<String>("Invalid user.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Set<UserRoles> authorities = user.getRoles();
        if (authorities == null) {
            authorities = new HashSet<>();
        }

        Role roleDef = roleService.findRoleByName(role);
        if (roleDef == null) {
            return new ResponseEntity<String>("Role undefined.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        UserRoles assignedRole = new UserRoles();
        assignedRole.setAuthority(role);
        assignedRole.setAssignedRole(roleDef);
        assignedRole.setUser(user);
        authorities.add(assignedRole);
        user.setRoles(authorities);
        userService.saveUser(user);
        return new ResponseEntity<String>("Role granted.", HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{user}/revoke/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> revokeRole(@PathVariable User user, @PathVariable String role) {
        if (user == null) {
            return new ResponseEntity<String>("Invalid user.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Set<UserRoles> authorities = user.getRoles();
        if (authorities != null) {
            for (UserRoles assignedRole : authorities) {
                if (assignedRole.getAuthority().equals(role)) {
                    authorities.remove(assignedRole);
                    userService.saveUser(user);
                    return new ResponseEntity<String>("Role revoked.", HttpStatus.OK);
                }
            }
            return new ResponseEntity<String>("The given role is not assigned to user.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<String>("The given user has no roles assigned.", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // @CrossOrigin
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}
