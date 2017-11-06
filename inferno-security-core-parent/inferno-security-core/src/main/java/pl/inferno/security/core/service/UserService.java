/**
 *
 */
package pl.inferno.security.core.service;

import java.util.List;

import pl.inferno.security.core.model.User;

/**
 * @author lukasz-adm
 *
 */
public interface UserService {

	User getUserByUserName(String username);

	List<User> getAllUsers();

	boolean isUserExist(User user);

	void saveUser(User user);

	User findById(long id);

}
