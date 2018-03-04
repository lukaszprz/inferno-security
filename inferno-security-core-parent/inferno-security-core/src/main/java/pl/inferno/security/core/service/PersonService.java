/**
 *
 */
package pl.inferno.security.core.service;

import java.util.Optional;
import java.util.Set;

import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;

/**
 * @author lukasz-adm
 *
 */
public interface PersonService {

	Person savePerson(Person person);

	Person getPerson(Long id);

	Set<Person> findPersonByFirstAndLastName(String firstName, String lastName);

	Optional<Person> findPersonByUser(User user);

}
