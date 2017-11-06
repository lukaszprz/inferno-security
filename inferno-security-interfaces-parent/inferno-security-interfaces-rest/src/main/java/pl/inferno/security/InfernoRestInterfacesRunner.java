/**
 *
 */
package pl.inferno.security;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.repository.RoleRepository;
import pl.inferno.security.core.repository.UserRepository;

/**
 * @author lukasz-adm
 *
 */
@SpringBootApplication
public class InfernoRestInterfacesRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// @Autowired
	// private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(InfernoRestInterfacesRunner.class, args);
	}

	// @PostConstruct
	public void initDBData() {

		Role roleAdmin = new Role();

		roleAdmin.setId(1L);
		roleAdmin.setName("ADMIN");
		roleRepository.save(roleAdmin);

		Role roleUser = new Role();

		roleUser.setId(2L);
		roleUser.setName("USER");
		roleRepository.save(roleUser);

		User userAdmin = new User();
		userAdmin.setId(1L);
		userAdmin.setUsername("inferno-admin");
		userAdmin.setPassword("inferno123");
		userAdmin.setActive(true);
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(roleAdmin);
		userAdmin.setRoles(adminRoles);
		userRepository.save(userAdmin);

		User user = new User();
		user.setId(2L);
		user.setUsername("inferno-user");
		user.setPassword("inferno123");
		user.setActive(true);
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(roleUser);
		user.setRoles(userRoles);
		User savedUser = userRepository.save(user);
		DateTime now = DateTime.now();
		DateTime nextMonth = now.plusMonths(1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 1);
		Timestamp validTo = new Timestamp(cal.getTimeInMillis());
		savedUser.setValidTo(validTo);
		userRepository.save(savedUser);

	}

	/**
	 * <p>
	 * passwordEncoder.
	 * </p>
	 *
	 * @return a
	 *         {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
	 *         object.
	 */
	// @Bean
	// public BCryptPasswordEncoder passwordEncoder() {
	// BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	// return bCryptPasswordEncoder;
	// }
}
