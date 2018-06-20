/**
 * Project: inferno-security-interfaces-web-db
 * File: InfernoWebConfiguration.java
 * Package: pl.inferno.security.configuration
 * Location:
 * 9 mar 2018 07:27:48 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.configuration;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import pl.inferno.security.converter.ObjectErrorToInfernoErrorObjectConverter;
import pl.inferno.security.converter.StringToTimestampConverter;

/**
 * Class InfernoWebConfiguration
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Configuration
@EnableWebMvc
public class InfernoWebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**", "/img/**", "/css/**", "/js/**", "/messages/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/", "classpath:/static/img/", "classpath:/static/css/",
				"classpath:/static/js/", "classpath:/messages/");
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setAlwaysUseMessageFormat(true);
		messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
		messageSource.setFallbackToSystemLocale(false);

		return messageSource;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
	 * getValidator()
	 */
	@Override
	public Validator getValidator() {

		return validator(messageSource());
	}

	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {

		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);

		return factory;
	}

	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.clearCache();
		resolver.setCache(false);
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		Set<String> aliases = StandardCharsets.UTF_8.aliases();
		StandardCharsets.UTF_8.displayName();

		resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		return resolver;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
	 * addFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {

		registry.addConverter(new ObjectErrorToInfernoErrorObjectConverter());
		registry.addConverter(new StringToTimestampConverter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
	 * configureViewResolvers(org.springframework.web.servlet.config.annotation.
	 * ViewResolverRegistry)
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(thymeleafViewResolver());
		super.configureViewResolvers(registry);
	}

}
