/**
 *
 */
package pl.inferno.security.core.service.impl;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.repository.UserRolesRepository;
import pl.inferno.security.core.service.UserRoleService;

/**
 * Class UserRoleAssigmentService
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Service
public class UserRoleAssigmentService implements UserRoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleAssigmentService.class);

	@Autowired
	private UserRolesRepository assigmentRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#findAllAssignedRoles(pl.
	 * inferno.security.core.model.User)
	 */
	@Override
	public List<UserRoles> findAllAssignedRoles(User user) {
		List<UserRoles> allAssignedRoles = new ArrayList<>();
		Set<UserRoles> assigments = assigmentRepository.findByUser(user);
		for (UserRoles assigment : assigments) {
			allAssignedRoles.add(assigment);
		}
		return allAssignedRoles;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#getAssignedRoleByRoleName(pl
	 * .inferno.security.core.model.User, java.lang.String)
	 */
	@Override
	public UserRoles getAssignedRoleByRoleName(User user, String roleName) {
		Set<UserRoles> assignedRoles = assigmentRepository.findByUser(user);
		for (UserRoles assignedRole : assignedRoles) {
			Role role = assignedRole.getAssignedRole();
			if (roleName.equals(role.getName())) {
				return assignedRole;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#findByRoleAssigmentId(java.
	 * lang.Long)
	 */
	@Override
	public UserRoles findByRoleAssigmentId(Long id) {

		return assigmentRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#
	 * getAllAssignedRolesByAuthority(java.lang.String)
	 */
	@Override
	public List<UserRoles> getAllAssignedRolesByAuthority(String authority) {

		return assigmentRepository.findByAuthority(authority);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#findAssignedRoleByAuthority(
	 * pl.inferno.security.core.model.User, java.lang.String)
	 */
	@Override
	public UserRoles findAssignedRoleByAuthority(User user, String authority) {
		Set<UserRoles> allAssigments = assigmentRepository.findByUser(user);
		for (UserRoles assigment : allAssigments) {
			if (assigment.getAuthority().equals(authority)) {
				return assigment;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#findAllValidAssignedRoles(pl
	 * .inferno.security.core.model.User)
	 */
	@Override
	public List<UserRoles> findAllValidAssignedRoles(User user) {
		LocalDateTime now = LocalDateTime.now();
		List<UserRoles> assignedRoles = assigmentRepository.findByValidFromBeforeAndValidToAfter(now, now);

		return assignedRoles;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#findAssignedRolesValidFrom(
	 * pl.inferno.security.core.model.User, org.joda.time.DateTime)
	 */
	@Override
	public List<UserRoles> findAssignedRolesValidFrom(User user, DateTime validFrom) {
		return assigmentRepository.findByUserAndValidFromAfter(user, validFrom.toLocalDateTime());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#findAssignedRolesValidTo(pl.
	 * inferno.security.core.model.User, org.joda.time.DateTime)
	 */
	@Override
	public List<UserRoles> findAssignedRolesValidTo(User user, DateTime validTo) {
		return assigmentRepository.findByUserAndValidToBefore(user, validTo.toLocalDateTime());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#
	 * findAllValidAssignedRolesWithPeriod(pl.inferno.security.core.model.User,
	 * org.joda.time.DateTime, org.joda.time.DateTime)
	 */
	@Override
	public List<UserRoles> findAllValidAssignedRolesWithPeriod(User user, DateTime validFrom, DateTime validTo) {
		return assigmentRepository.findByUserAndValidFromAfterAndValidToBefore(user, validFrom, validTo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#getAllValidAssigmentByPeriod
	 * (org.joda.time.DateTime, org.joda.time.DateTime)
	 */
	@Override
	public List<UserRoles> getAllValidAssigmentByPeriod(DateTime validFrom, DateTime validTo) {
		return assigmentRepository.findAllByValidFromAfterAndValidToBefore(validFrom, validTo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#isRoleAssignedToUser(pl.
	 * inferno.security.core.model.User, pl.inferno.security.core.model.Role)
	 */
	@Override
	public boolean isRoleAssignedToUser(User user, Role role) {
		Set<UserRoles> assigments = assigmentRepository.findByUser(user);
		for (UserRoles assigment : assigments) {
			Role assignedRole = assigment.getAssignedRole();
			if (role.getName().equals(assignedRole.getName())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#hasUserRole(pl.inferno.
	 * security.core.model.User, pl.inferno.security.core.model.Role)
	 */
	@Override
	public boolean hasUserRole(User user, Role role) {
		Set<UserRoles> assigments = assigmentRepository.findByUser(user);
		for (UserRoles assigment : assigments) {
			Role assignedRole = assigment.getAssignedRole();
			if (role.getName().equals(assignedRole.getName())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#
	 * doesUserContainedAssignedRoles(pl.inferno.security.core.model.User,
	 * java.util.List)
	 */
	@Override
	public boolean doesUserContainedAssignedRoles(User user, List<Role> roles) {
		Set<UserRoles> assigments = assigmentRepository.findByUser(user);
		for (UserRoles assigment : assigments) {
			Role assignedRole = assigment.getAssignedRole();
			return roles.contains(assignedRole);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#isRoleAssigmentValid(pl.
	 * inferno.security.core.model.User, pl.inferno.security.core.model.Role)
	 */
	@Override
	public boolean isRoleAssigmentValid(User user, Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#assignRole(pl.inferno.
	 * security.core.model.User, pl.inferno.security.core.model.Role,
	 * org.joda.time.DateTime, org.joda.time.DateTime, java.lang.String)
	 */
	@Override
	public UserRoles assignRole(Principal principal, User user, Role role, DateTime validFrom, DateTime validTo,
			String authority) {
		UserRoles assigment = new UserRoles();
		assigment.setUser(user);
		assigment.setAssignedRole(role);
		assigment.setAuthority(authority);
		assigment.setCreatedBy(principal.getName());
		assigment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		if (validFrom != null)
			assigment.setValidFrom(new Timestamp(validFrom.toDateTime().getMillis()));
		if (validTo != null)
			assigment.setValidTo(new Timestamp(validTo.toDateTime().getMillis()));
		if (!isRoleAssignedToUser(user, role))
			return assigmentRepository.save(assigment);
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#updateAssignedRole(pl.
	 * inferno.security.core.model.User, pl.inferno.security.core.model.Role)
	 */
	@Override
	public UserRoles updateAssignedRole(Principal principal, User user, Role role) {
		UserRoles assigment = assigmentRepository.findByUserAndAssignedRole(user, role);
		assigment.setUser(user);
		assigment.setAssignedRole(role);
		assigment.setLastModifiedBy(principal.getName());
		assigment.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));

		return assigmentRepository.save(assigment);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#
	 * updateAssignedRoleByAssigment(pl.inferno.security.core.model.UserRoles)
	 */
	@Override
	public UserRoles updateAssignedRoleByAssigment(Principal principal, UserRoles assigment) {

		assigment.setLastModifiedBy(principal.getName());
		assigment.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));

		return assigmentRepository.save(assigment);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#updateValidTo(pl.inferno.
	 * security.core.model.UserRoles, org.joda.time.DateTime)
	 */
	@Override
	public UserRoles updateValidTo(Principal principal, UserRoles assigment, DateTime validTo) {
		assigment.setValidTo(new Timestamp(validTo.getMillis()));
		assigment.setLastModifiedBy(principal.getName());
		assigment.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));

		return assigmentRepository.save(assigment);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#updateValidFrom(pl.inferno.
	 * security.core.model.UserRoles, org.joda.time.DateTime)
	 */
	@Override
	public UserRoles updateValidFrom(Principal principal, UserRoles assigment, DateTime validFrom) {
		assigment.setValidFrom(new Timestamp(validFrom.getMillis()));
		assigment.setLastModifiedBy(principal.getName());
		assigment.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));

		return assigmentRepository.save(assigment);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.UserRoleService#save(pl.inferno.security.
	 * core.model.UserRoles)
	 */
	@Override
	public UserRoles save(Principal principal, UserRoles assigment) {
		assigment.setLastModifiedBy(principal.getName());
		assigment.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));

		return assigmentRepository.save(assigment);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#saveAll(java.util.List)
	 */
	@Override
	public List<UserRoles> saveAll(Principal principal, List<UserRoles> assigmentList) {
		List<UserRoles> savedAssigned = new ArrayList<>();
		for (UserRoles assigment : assigmentList) {
			assigment.setLastModifiedBy(principal.getName());
			assigment.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			savedAssigned.add(assigment);
		}
		return assigmentRepository.save(savedAssigned);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.UserRoleService#revokeAssignedRole(pl.
	 * inferno.security.core.model.User, pl.inferno.security.core.model.Role)
	 */
	@Override
	public boolean revokeAssignedRole(Principal principal, User user, Role role) {
		UserRoles assigment = assigmentRepository.findByUserAndAssignedRole(user, role);
		assigmentRepository.delete(assigment);

		return !assigmentRepository.exists(assigment.getId());
	}
}
