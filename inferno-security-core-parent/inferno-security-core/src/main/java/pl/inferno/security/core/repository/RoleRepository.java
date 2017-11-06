/**
 * 
 */
package pl.inferno.security.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.inferno.security.core.model.Role;

/**
 * @author lukasz-adm
 *
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
