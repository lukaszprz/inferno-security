/**
 *
 */
package pl.inferno.security.core.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;

/**
 * @author lukasz-adm
 */
@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, User> {

    Set<UserRoles> findByUser(User user);

}
