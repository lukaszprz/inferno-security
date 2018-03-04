/**
 *
 */
package pl.inferno.security.core.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;

/**
 * @author lukasz-adm
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Set<Person> findByFirstNameAndLastName(String firstName, String lastName);

	Optional<Person> findByUser(User user);

}
