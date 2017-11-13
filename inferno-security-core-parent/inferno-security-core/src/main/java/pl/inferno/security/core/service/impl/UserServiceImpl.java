/**
 *
 */
package pl.inferno.security.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.repository.UserRepository;
import pl.inferno.security.core.service.UserService;

/**
 * @author lukasz-adm
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

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
		return userRepository.findAll();
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
		// TODO Auto-generated method stub
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findOneByUsername(username);
	}

}
