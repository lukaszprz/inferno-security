/**
 *
 */
package pl.inferno.security.core.service;

import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;

/**
 * @author lukasz-adm
 *
 */
public interface AddressService {

    Address saveAddress(Address address);

    Address getAddressByType(AddressType type);

    Address findAddress(Address address);

    Address findById(Long id);

    Address deleteAddressById(Long id);

}
