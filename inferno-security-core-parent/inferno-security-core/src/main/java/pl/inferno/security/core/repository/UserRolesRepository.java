/**
 *
 */
package pl.inferno.security.core.repository;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;

/**
 * @author lukasz-adm
 */
@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    Set<UserRoles> findByUser(User user);

    List<UserRoles> findByAuthority(String authority);

    List<UserRoles> findByValidFromBeforeAndValidToAfter(DateTime from, DateTime to);

    /**
     * @param now
     * @param now2
     * @return
     */
    List<UserRoles> findByValidFromBeforeAndValidToAfter(LocalDateTime now, LocalDateTime now2);

    List<UserRoles> findByUserAndValidFromAfter(User user, LocalDateTime validFrom);

    List<UserRoles> findByUserAndValidToBefore(User user, LocalDateTime validTo);

    List<UserRoles> findByUserAndValidFromAfterAndValidToBefore(User user, DateTime validFrom, DateTime validTo);

    List<UserRoles> findAllByValidFromAfterAndValidToBefore(DateTime validFrom, DateTime validTo);

    UserRoles findByUserAndAssignedRole(User user, Role role);
}
