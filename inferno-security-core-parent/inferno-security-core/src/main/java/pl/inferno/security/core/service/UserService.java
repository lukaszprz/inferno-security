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

    boolean isUserExists(String username);

    User saveUser(User user);

    User findById(long id);

    User getCurrentUser(String username);

    User getCurrentUser(Long id);

    void deleteUser(User user);

}
