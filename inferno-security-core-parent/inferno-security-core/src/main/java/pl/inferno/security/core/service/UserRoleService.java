/**
 *
 */
package pl.inferno.security.core.service;

import java.security.Principal;
import java.util.List;

import org.joda.time.DateTime;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;

/**
 * Class UserRoleService
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public interface UserRoleService {

    List<UserRoles> findAllAssignedRoles(User user);

    UserRoles getAssignedRoleByRoleName(User user, String roleName);

    UserRoles findByRoleAssigmentId(Long id);

    List<UserRoles> getAllAssignedRolesByAuthority(String authority);

    UserRoles findAssignedRoleByAuthority(User user, String authority);

    List<UserRoles> findAllValidAssignedRoles(User user);

    List<UserRoles> findAssignedRolesValidFrom(User user, DateTime validFrom);

    List<UserRoles> findAssignedRolesValidTo(User user, DateTime validTo);

    List<UserRoles> findAllValidAssignedRolesWithPeriod(User user, DateTime validFrom, DateTime validTo);

    List<UserRoles> getAllValidAssigmentByPeriod(DateTime validFrom, DateTime validTo);

    boolean isRoleAssignedToUser(User user, Role role);

    boolean hasUserRole(User user, Role role);

    boolean doesUserContainedAssignedRoles(User user, List<Role> roles);

    boolean isRoleAssigmentValid(User user, Role role);

    UserRoles assignRole(Principal principal, User user, Role role, DateTime validFrom, DateTime validTo,
	    String authority);

    UserRoles updateAssignedRole(Principal principal, User user, Role role);

    UserRoles updateAssignedRoleByAssigment(Principal principal, UserRoles assigment);

    UserRoles updateValidTo(Principal principal, UserRoles assigment, DateTime validTo);

    UserRoles updateValidFrom(Principal principal, UserRoles assigment, DateTime validFrom);

    UserRoles save(Principal principal, UserRoles assigment);

    List<UserRoles> saveAll(Principal principal, List<UserRoles> assigmentList);

    boolean revokeAssignedRole(Principal principal, User user, Role role);

}
