/**
 *
 */
package pl.inferno.security.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.repository.UserRepository;
import pl.inferno.security.core.service.UserService;

/**
 * @author lukasz-adm
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#getUserByUserName(java.lang.
     * String)
     */
    @Override
    public User getUserByUserName(String username) {
	return userRepository.findOneByUsername(username);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.inferno.security.core.service.UserService#getAllUsers()
     */
    @Override
    public List<User> getAllUsers() {
	List<User> users = userRepository.findAll();
	return users;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#isUserExist(pl.inferno.security.
     * core.model.User)
     */
    @Override
    public boolean isUserExist(User user) {

	return userRepository.exists(user.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#saveUser(pl.inferno.security.
     * core.model.User)
     */
    @Override
    public User saveUser(User user) {
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	return userRepository.save(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.inferno.security.core.service.UserService#findById(long)
     */
    @Override
    public User findById(long id) {
	return userRepository.findOne(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#getCurrentUser(java.lang.String)
     */
    @Override
    public User getCurrentUser(String username) {
	return userRepository.findOneByUsername(username);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#deleteUser(pl.inferno.security.
     * core.model.User)
     */
    @Override
    public void deleteUser(User user) {
	userRepository.delete(user);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.findOneByUsername(username);
	if (user == null) {
	    throw new UsernameNotFoundException(username);
	}
	return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#getCurrentUser(java.lang.Long)
     */
    @Override
    public User getCurrentUser(Long id) {
	return userRepository.findOne(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.core.service.UserService#isUserExists(java.lang.String)
     */
    @Override
    public boolean isUserExists(String username) {
	Optional<User> user = userRepository.findUserByUsername(username);
	return user.isPresent();
    }

}
