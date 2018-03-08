/**
 *
 */
package pl.inferno.security.proxy.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.proxy.service.UserProxyService;

/**
 * @author lukasz-adm
 */
@Service
public class UserProxyServiceImpl implements UserProxyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProxyServiceImpl.class);

    private static String SERVER_URL;

    private static String SERVER_ADMIN_URL;

    public String URL_USER = SERVER_URL + URL_PATH_SEPARATOR + URL_PATH_USER;

    public String URL_USER_BY_USERNAME = URL_USER + URL_PATH_SEPARATOR + URL_PATH_NAME;

    public String URL_ADMIN_ALL_USERS = SERVER_ADMIN_URL + URL_PATH_SEPARATOR + URL_PATH_USERS;

    private String url;

    /*
     * (non-Javadoc)
     * @see
     * pl.inferno.security.proxy.service.UserProxyService#getUserByUsername(java.
     * lang.String)
     */
    @Override
    public UserDTO getUserByUsername(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrl(UserProxyServiceType.USER) + URL_PATH_SEPARATOR + URL_PATH_USER + URL_PATH_SEPARATOR + URL_PATH_NAME + URL_PATH_SEPARATOR + username;

        ResponseEntity<UserDTO> entity = restTemplate.getForEntity(url, UserDTO.class);

        if (!entity.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }
        return entity.getBody();
    }

    /*
     * (non-Javadoc)
     * @see pl.inferno.security.proxy.service.UserProxyService#getUserById(long)
     */
    @Override
    public UserDTO getUserById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrl(UserProxyServiceType.USER) + URL_PATH_SEPARATOR + URL_PATH_USER + URL_PATH_SEPARATOR + id;
        ResponseEntity<UserDTO> entity = restTemplate.getForEntity(url, UserDTO.class);

        if (!entity.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }
        return entity.getBody();
    }

    @Override
    public ResponseEntity<?> getCurrentUser(String authorizationToken) {
        RestTemplate restTemplate = new RestTemplate();
        url = getUrl(UserProxyServiceType.USER) + URL_PATH_SEPARATOR + URL_PATH_USERS + URL_PATH_SEPARATOR + URL_PATH_CURRENT;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(HEADER_X_AUTH_TOKEN, authorizationToken);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserDTO> currentPrincipal = restTemplate.exchange(url, HttpMethod.GET, request, UserDTO.class);
        if (currentPrincipal.getStatusCode().equals(HttpStatus.FOUND)) {
            UserDTO currentUser = currentPrincipal.getBody();
            currentUser.setToken(authorizationToken);
            return new ResponseEntity<UserDTO>(currentUser, currentPrincipal.getHeaders(), HttpStatus.FOUND);
        }
        return currentPrincipal;

    }

    /*
     * (non-Javadoc)
     * @see pl.inferno.security.proxy.service.UserProxyService#getAllUsers()
     */
    @Override
    public List<UserDTO> getAllUsers(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrl(UserProxyServiceType.ADMIN) + URL_PATH_SEPARATOR + URL_PATH_USERS;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(HEADER_X_AUTH_TOKEN, token);
        HttpEntity<String> testRequest = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserDTO[]> entity = restTemplate.exchange(url, HttpMethod.GET, testRequest, UserDTO[].class);

        if (!entity.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }
        if (entity.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return new ArrayList<>();
        }
        UserDTO[] allUsers = entity.getBody();
        List<UserDTO> resultList = Arrays.asList(allUsers);
        return resultList;

    }

    /*
     * (non-Javadoc)
     * @see
     * pl.inferno.security.proxy.service.UserProxyService#setServerUrl(java.lang.
     * String)
     */
    @Value("${api.server.url}")
    @Override
    public void setServerUrl(String url) {
        SERVER_URL = url;
    }

    /*
     * (non-Javadoc)
     * @see
     * pl.inferno.security.proxy.service.UserProxyService#setServerAdminUrl(java.
     * lang.String)
     */
    @Value("${api.server.admin.url}")
    @Override
    public void setServerAdminUrl(String url) {
        SERVER_ADMIN_URL = url;
    }

    /**
     * @return the url
     */
    public String getUrl(UserProxyServiceType userProxyServiceType) {
        switch (userProxyServiceType) {
            case ADMIN:
                url = SERVER_ADMIN_URL;
                break;
            case USER:
                url = SERVER_URL;
                break;
            default:
                url = URL_PATH_SEPARATOR;
                break;
        }
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * @see
     * pl.inferno.security.proxy.service.UserProxyService#login(pl.inferno.security.
     * proxy.dto.UserDTO)
     */
    @Override
    public ResponseEntity<String> login(UserDTO user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrl(UserProxyServiceType.USER) + URL_PATH_SEPARATOR + URL_PATH_LOGIN;
        String username = user.getUsername();
        String password = user.getPassword();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> loginRequest = new HttpEntity<>("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}", httpHeaders);
        ResponseEntity<UserDTO> results = restTemplate.postForEntity(url, loginRequest, UserDTO.class);
        if (results.getStatusCode().equals(HttpStatus.OK)) {
            user.setToken(results.getHeaders().getFirst(HEADER_X_AUTH_TOKEN));
            return new ResponseEntity<String>(user.getToken(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>(results.getStatusCode());

    }

    /*
     * (non-Javadoc)
     * @see
     * pl.inferno.security.proxy.service.UserProxyService#encryptPassword(java.lang.
     * String)
     */
    @Override
    public HttpEntity<String> encryptPassword(String rawPassword) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrl(UserProxyServiceType.USER) + URL_PATH_SEPARATOR + URL_PATH_ENCRYPT + "?rawPassword=" + rawPassword;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> passwordRequest = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        return result;
    }

    @Override
    public HttpEntity<UserDTO> getToken(HttpHeaders httpHeaders) {
        RestTemplate restTemplate = new RestTemplate();
        url = getUrl(UserProxyServiceType.USER) + URL_PATH_SEPARATOR + URL_PATH_USERS + URL_PATH_SEPARATOR + URL_PATH_CURRENT;

        LOGGER.debug("================ SERVICE getToken(HttpHeaders httpHeaders) ============================");

        LOGGER.debug("PARAM httpHeaders: {}", httpHeaders);

        String token = httpHeaders.getFirst(HEADER_X_AUTH_TOKEN);
        LOGGER.debug("Token wyciagniety z headera: {}", token);
        HttpHeaders newHeaders = new HttpHeaders();
        LOGGER.debug("Tworze nowy zestaw headerów: {}", newHeaders);
        newHeaders.add(HEADER_X_AUTH_TOKEN, token);
        LOGGER.debug("Dodaje tokena do X-AUTH-TOKEN: {}", newHeaders);
        HttpEntity<String> authorizationRequest = new HttpEntity<String>(newHeaders);
        LOGGER.debug("Przygotowuje requesta do operacji getCurrent()... {}", authorizationRequest);
        UserDTO user = new UserDTO();

        ResponseEntity<UserDTO> currentPrincipal = restTemplate.exchange(url, HttpMethod.GET, authorizationRequest, UserDTO.class);
        LOGGER.debug("currentPrincipal: {}", currentPrincipal);

        if ((currentPrincipal != null) && (currentPrincipal.getStatusCode().equals(HttpStatus.ACCEPTED) || currentPrincipal.getStatusCode().equals(HttpStatus.FOUND))) {

            HttpHeaders headers = currentPrincipal.getHeaders();
            UserDTO auth = currentPrincipal.getBody();
            if (auth.isAuthenticated()) {
                auth.setAuthenticated(true);
                List<Collection<? extends GrantedAuthority>> authorities = Arrays.asList(auth.getAuthorities());
                Principal credentials = (Principal) auth.getCredentials();
                UserDetails details = (UserDetails) auth.getDetails();
                String name = auth.getName();
                Object principal = auth.getPrincipal();
                user = auth;

                // Set<RoleDTO> roles = new HashSet<>();
                // for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                // RoleDTO role = new RoleDTO(grantedAuthority.getAuthority(),
                // grantedAuthority.getAuthority());
                // roles.add(role);
                // }
                // user.setRoles(roles);
                //
                // user.setActive(details.isEnabled());
                // user.setAuthenticated(true);
                // user.setCreated(DateTime.now());
            }

            // UserDTO authenticatedUser = currentPrincipal.;

            // user.setActive(currentPrincipal.getBody().getDetails());
            return new HttpEntity<UserDTO>(user, currentPrincipal.getHeaders());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
