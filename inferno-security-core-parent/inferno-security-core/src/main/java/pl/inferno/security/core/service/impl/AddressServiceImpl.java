/**
 *
 */
package pl.inferno.security.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;
import pl.inferno.security.core.repository.AddressRepository;
import pl.inferno.security.core.service.AddressService;

/**
 * @author lukasz-adm
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

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
     * @see pl.inferno.security.core.service.AddressService#findById(java.lang.Long)
     */
    @Override
    public Address findById(Long id) {
	return addressRepository.findOne(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.AddressService#deleteAddressById(java.lang.
     * Long)
     */
    @Override
    public Address deleteAddressById(Long id) {
	Address deletedAddress = addressRepository.findOne(id);
	if (deletedAddress != null) {
	    addressRepository.delete(id);
	}
	return deletedAddress;
    }

}
