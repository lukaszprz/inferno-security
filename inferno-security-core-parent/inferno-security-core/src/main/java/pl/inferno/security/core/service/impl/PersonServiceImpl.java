/**
 *
 */
package pl.inferno.security.core.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.repository.PersonRepository;
import pl.inferno.security.core.service.PersonService;

/**
 * @author lukasz-adm
 *
 */
@Service
class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.PersonService#savePerson(pl.inferno.security
	 * .core.model.Person)
	 */
	@Override
	public Person savePerson(Person person) {
		return personRepository.saveAndFlush(person);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.PersonService#getPerson(java.lang.Long)
	 */
	@Override
	public Person getPerson(Long id) {
		return personRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.PersonService#findPersonByFirstAndLastName(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Set<Person> findPersonByFirstAndLastName(String firstName, String lastName) {
		return personRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.PersonService#findPersonByUser(pl.inferno.
	 * security.core.model.User)
	 */
	@Override
	public Optional<Person> findPersonByUser(User user) {
		return personRepository.findByUser(user);
	}

}
