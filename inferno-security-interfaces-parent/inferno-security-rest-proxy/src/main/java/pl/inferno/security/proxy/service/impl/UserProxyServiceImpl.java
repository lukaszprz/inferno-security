/**
 *
 */
package pl.inferno.security.proxy.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.proxy.service.UserProxyService;

/**
 * @author lukasz-adm
 *
 */
@Service
public class UserProxyServiceImpl implements UserProxyService {

	private Logger LOGGER = LoggerFactory.getLogger(UserProxyServiceImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.inferno.security.proxy.service.UserProxyService#getUserByUsername(java.
	 * lang.String)
	 */
	@Override
	public UserDTO getUserByUsername(String username) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDTO> entity = restTemplate.getForEntity("http://localhost:8001/api/user/name/" + username,
		        UserDTO.class);

		LOGGER.info("getUserByUsername : {}", entity.getStatusCodeValue());

		if (!entity.getStatusCode().equals(HttpStatus.OK)) {
			LOGGER.info("getUserByUsername returned STATUS CODE : {} with reason : {}", entity.getStatusCodeValue(),
			        entity.getStatusCode().getReasonPhrase());
			return null;
		}
		return entity.getBody();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.proxy.service.UserProxyService#getUserById(long)
	 */
	@Override
	public UserDTO getUserById(long id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDTO> entity = restTemplate.getForEntity("http://localhost:8001/api/user/" + id,
		        UserDTO.class);

		LOGGER.info("getUserById : {}", entity.getStatusCodeValue());

		if (!entity.getStatusCode().equals(HttpStatus.OK)) {
			LOGGER.info("getUserByUsername returned STATUS CODE : {} with reason : {}", entity.getStatusCodeValue(),
			        entity.getStatusCode().getReasonPhrase());
			return null;
		}
		return entity.getBody();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.proxy.service.UserProxyService#getAllUsers()
	 */
	@Override
	public List<UserDTO> getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		UserDTO[] allUsers = restTemplate.getForObject("http://localhost:8001/api/user/", UserDTO[].class);
		List<UserDTO> resultList = Arrays.asList(allUsers);
		return resultList;
	}

}
