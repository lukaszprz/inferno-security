/**
 *
 */
package pl.inferno.security.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>DatabaseInitialization class.</p>
 *
 * @author lukasz-adm
 * @version $Id: $Id
 */
@Component
public class DatabaseInitialization {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitialization.class);

	@Autowired
	private DataSource dataSource;

	@Value("${spring.database.superuser.name}")
	private String dbAdmin;

	@Value("${spring.database.superuser.password}")
	private String dbAdminPassword;

	/**
	 * <p>initDB.</p>
	 */
	@PostConstruct
	public void initDB() {
		LOGGER.info("Checking database for superuser role...");
		Connection connection = null;
		boolean isConnectionOpenned = false;
		boolean isConnectionValid = false;
		boolean isConnectionReadable = false;
		try {
			connection = dataSource.getConnection(dbAdmin, dbAdminPassword);
			isConnectionOpenned = !connection.isClosed();
			isConnectionValid = connection.isValid(10);
			isConnectionReadable = !connection.isReadOnly();
		} catch (SQLException e) {
			LOGGER.error("Cannot connect to database! No superuser role was found or configuration is wrong.", e);
		}

		if (connection != null) {

			if (isConnectionOpenned && isConnectionValid && isConnectionReadable) {
				LOGGER.info("Syperuser with name {} exists.", dbAdmin);
				LOGGER.info("No need to iniotialize database. (SKIP_INIT_DB)");
			} else {
				LOGGER.warn("Missing superuser in database!");
				LOGGER.info("Initializing database...");
				try {
					connection.createStatement().execute(
					        "INSERT INTO \"inferno-authorization-schema\".\"inferno_users\" (\"user_id\", \"active\", \"created\", \"password\", \"username\", \"valid_to\") VALUES (1, true, '2017-11-01 03:23:59.546442', '$2a$08$Ly4o/t4BMgpQPsjF4/SB1OFGCwwq7VBDyprhdkhv57SJ1vSAfOXgS', 'inferno-admin', NULL);");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
