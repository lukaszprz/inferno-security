/**
 *
 */
package pl.inferno.security.core.service;

import java.util.Set;

import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;
import pl.inferno.security.core.model.Person;

/**
 * @author lukasz-adm
 *
 */
public interface AddressService {

	Address saveAddress(Address address);

	Address getAddressByType(AddressType type);

	Address findAddress(Address address);

	Set<Address> findAddressesByPerson(Person person);

}
