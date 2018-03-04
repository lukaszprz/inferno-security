/**
 *
 */
package pl.inferno.security.core.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.repository.RoleRepository;
import pl.inferno.security.core.repository.UserRepository;
import pl.inferno.security.core.repository.UserRolesRepository;
import pl.inferno.security.core.service.RoleService;

/**
 * @author lukasz-adm
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#findRoleById(java.lang.Long)
	 */
	@Override
	public Role findRoleById(Long id) {
		return roleRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#findRoleByName(java.lang.String)
	 */
	@Override
	public Role findRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#saveRole(pl.inferno.security.
	 * core.model.Role)
	 */
	@Override
	public boolean saveRole(Role role) {
		// boolean isExists = (role != null) && roleRepository.exists(role.getId());
		// if (!isExists) {
		Role savedRole = roleRepository.saveAndFlush(role);
		if ((role != null) && roleRepository.exists(savedRole.getId())) {
			return true;
		}
		// }
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#deleteRole(pl.inferno.security.
	 * core.model.Role)
	 */
	@Override
	public boolean deleteRole(Role role) {
		boolean isExists = (role != null) && roleRepository.exists(role.getId());
		if (!isExists) {
			return false;
		} else {
			roleRepository.delete(role);
		}
		return !roleRepository.exists(role.getId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#deleteRoleById(java.lang.Long)
	 */
	@Override
	public boolean deleteRoleById(Long id) {
		boolean isExists = (id != null) && roleRepository.exists(id);
		if (!isExists) {
			return false;
		} else {
			roleRepository.delete(id);
		}
		return !roleRepository.exists(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.RoleService#deleteRoleByName(java.lang.
	 * String)
	 */
	@Override
	public boolean deleteRoleByName(String name) {
		Role role = roleRepository.findByName(name);
		if (role != null) {
			roleRepository.delete(role);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#updateRole(pl.inferno.security.
	 * core.model.Role)
	 */
	@Override
	public Role updateRole(Role role) {
		boolean isExists = (role != null) && roleRepository.exists(role.getId());
		if (isExists) {
			return roleRepository.saveAndFlush(role);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.RoleService#findAllRoles()
	 */
	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.RoleService#saveUserRoles(pl.inferno.
	 * security.core.model.UserRoles)
	 */
	@Override
	public void saveUserRoles(UserRoles userRoles) {
		userRolesRepository.save(userRoles);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#isRoleExists(java.lang.String)
	 */
	@Override
	public boolean isRoleExists(String roleName) {
		Role role = roleRepository.findByName(roleName);
		if ((role != null) && roleRepository.exists(role.getId())) {
			return true;
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#isGivenRoleAssignedToUser(java.
	 * lang.String, pl.inferno.security.core.model.User)
	 */
	@Override
	public boolean isGivenRoleAssignedToUser(String roleName, String username) {
		User user = userRepository.findOneByUsername(username);
		if (user == null) {
			return false;
		}
		Set<UserRoles> assignedRoles = user.getRoles();
		Role foundRole = roleRepository.findByName(roleName);
		for (UserRoles assignedRole : assignedRoles) {

			return assignedRole.getAssignedRole().equals(foundRole);

		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.RoleService#doesUserHasAllGivenRoles(java.
	 * util.Set, pl.inferno.security.core.model.User)
	 */
	@Override
	public boolean doesUserHasAllGivenRoles(Set<Role> expectedRolesSet, String username) {
		boolean check = false;
		User user = userRepository.findOneByUsername(username);
		if (user == null) {
			return check;
		}
		Set<UserRoles> userRoles = user.getRoles();
		for (Role expectedRole : expectedRolesSet) {
			for (UserRoles assignedRole : userRoles) {
				check = assignedRole.getAssignedRole().equals(expectedRole);
			}
		}
		return check;
	}

}
