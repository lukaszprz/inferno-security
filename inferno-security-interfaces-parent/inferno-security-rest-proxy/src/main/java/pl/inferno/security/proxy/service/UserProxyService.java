/**
 *
 */
package pl.inferno.security.proxy.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import pl.inferno.security.proxy.dto.UserDTO;

/**
 * @author lukasz-adm
 */
public interface UserProxyService {

    enum UserProxyServiceType {
        USER, ADMIN

    };

    public static final String HEADER_X_AUTH_TOKEN = "X-AUTH-TOKEN";

    public static final String URL_PATH_SEPARATOR = "/";

    public static final String URL_PATH_USER = "user";

    public static final String URL_PATH_USERS = "users";

    public static final String URL_PATH_NAME = "name";

    public static final String URL_PATH_LOGIN = "login";

    public static final String URL_PATH_ENCRYPT = "encrypt";

    public static final String URL_PATH_CURRENT = "current";

    // public static final String URL_USER = SERVER_URL + URL_PATH_SEPARATOR +
    // URL_PATH_USER;

    // public static final String URL_USER_BY_USERNAME = URL_USER +
    // URL_PATH_SEPARATOR + URL_PATH_NAME;

    // public static final String URL_ADMIN_ALL_USERS = SERVER_ADMIN_URL +
    // URL_PATH_SEPARATOR + URL_PATH_USERS;

    UserDTO getUserByUsername(String username);

    UserDTO getUserById(long id);

    ResponseEntity<?> getCurrentUser(String authorizationToken);

    HttpEntity<UserDTO> getToken(HttpHeaders headers);

    List<UserDTO> getAllUsers(String token);

    void setServerUrl(String url);

    void setServerAdminUrl(String url);

    ResponseEntity<String> login(UserDTO user);

    HttpEntity<String> encryptPassword(String rawPassword);

}
