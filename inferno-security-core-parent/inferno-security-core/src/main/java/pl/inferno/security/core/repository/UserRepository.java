/**
 *
 */
package pl.inferno.security.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.inferno.security.core.model.User;

/**
 * @author lukasz-adm
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);

    Optional<User> findUserByUsername(String username);
}
