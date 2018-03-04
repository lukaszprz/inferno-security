/**
 * Project: inferno-security-interfaces-rest
 * File: RestConfiguration.java
 * Package: pl.inferno.security.interfaces.rest.configuration
 * Location:
 *
 * 2 mar 2018 22:29:09 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class RestConfiguration
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Configuration
@EnableWebMvc
public class RestConfiguration extends WebMvcConfigurerAdapter {

	private ObjectMapper objectMapper() {
		Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
		bean.setIndentOutput(true);
		bean.setSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		bean.afterPropertiesSet();
		ObjectMapper objectMapper = bean.getObject();
		// objectMapper.registerModule(new JodaModule());
		return objectMapper;
	}

	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
	}

}
