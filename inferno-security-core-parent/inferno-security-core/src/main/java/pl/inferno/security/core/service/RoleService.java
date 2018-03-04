/**
 *
 */
package pl.inferno.security.core.service;

import java.util.List;
import java.util.Set;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.UserRoles;

/**
 * @author lukasz-adm
 *
 */
public interface RoleService {

	Role findRoleById(Long id);

	Role findRoleByName(String name);

	boolean saveRole(Role role);

	boolean deleteRole(Role role);

	boolean deleteRoleById(Long id);

	boolean deleteRoleByName(String name);

	boolean isRoleExists(String roleName);

	boolean isGivenRoleAssignedToUser(String roleName, String username);

	boolean doesUserHasAllGivenRoles(Set<Role> expectedRolesSet, String username);

	Role updateRole(Role role);

	List<Role> findAllRoles();

	void saveUserRoles(UserRoles userRoles);

}
