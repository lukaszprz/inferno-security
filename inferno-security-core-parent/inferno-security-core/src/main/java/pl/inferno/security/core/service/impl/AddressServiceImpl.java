/**
 *
 */
package pl.inferno.security.core.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;
import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.repository.AddressRepository;
import pl.inferno.security.core.service.AddressService;

/**
 * @author lukasz-adm
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.AddressService#saveAddress(pl.inferno.
	 * security.core.model.Address)
	 */
	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.AddressService#getAddressByType(java.lang.
	 * String)
	 */
	@Override
	public Address getAddressByType(AddressType type) {
		return addressRepository.findByType(type);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.core.service.AddressService#findAddress(pl.inferno.
	 * security.core.model.Address)
	 */
	@Override
	public Address findAddress(Address address) {

		return addressRepository.findByCityAndPostCodeAndStreetAndBuildingNumberAndAppartmentAndType(address.getCity(),
		        address.getPostCode(), address.getStreet(), address.getBuildingNumber(), address.getAppartment(),
		        address.getType());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.core.service.AddressService#findAddressesByPerson(pl.
	 * inferno.security.core.model.Person)
	 */
	@Override
	public Set<Address> findAddressesByPerson(Person person) {
		return addressRepository.findByPerson(person);
	}

}
