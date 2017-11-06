/**
 *
 */
package pl.inferno.security.proxy.service;

import java.util.List;

import pl.inferno.security.proxy.dto.UserDTO;

/**
 * @author lukasz-adm
 *
 */
public interface UserProxyService {

	UserDTO getUserByUsername(String username);

	UserDTO getUserById(long id);

	List<UserDTO> getAllUsers();

}
