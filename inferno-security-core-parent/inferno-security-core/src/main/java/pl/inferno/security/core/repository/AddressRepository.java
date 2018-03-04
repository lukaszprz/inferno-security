/**
 *
 */
package pl.inferno.security.core.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;
import pl.inferno.security.core.model.Person;

/**
 * @author lukasz-adm
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	/**
	 * @param type
	 * @return
	 */
	Address findByType(AddressType type);

	Address findByCityAndPostCodeAndStreetAndBuildingNumberAndAppartmentAndType(String city, String postCode,
	        String street, String buildingNumber, String Appartment, AddressType type);

	Set<Address> findByPerson(Person person);

}
