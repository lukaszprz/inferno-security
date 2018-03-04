/**
 * Project: inferno-security-interfaces-rest
 * File: RestConfiguration.java
 * Package: pl.inferno.security.interfaces.rest.configuration
 * Location:
 *
 * 2 mar 2018 22:29:09 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.interfaces.rest.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, true);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
		objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		objectMapper.configure(Feature.ALLOW_COMMENTS, true);
		objectMapper.configure(Feature.ALLOW_MISSING_VALUES, true);
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(Feature.ALLOW_YAML_COMMENTS, true);
		objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

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
