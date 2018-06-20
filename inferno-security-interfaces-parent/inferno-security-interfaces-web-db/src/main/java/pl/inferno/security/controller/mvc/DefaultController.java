/**
 * Project: inferno-security-interfaces-web-db
 * File: DefaultController.java
 * Package: pl.inferno.security.controller.mvc
 * Location:
 * 9 mar 2018 07:31:03 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.controller.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.inferno.security.converter.ObjectErrorToInfernoErrorObjectConverter;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.validator.UserValidator;

/**
 * Class DefaultController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Controller
public class DefaultController {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private ObjectErrorToInfernoErrorObjectConverter objectErrorConverter;

	@InitBinder("userForm")
	protected void initBinder(WebDataBinder binder) {

		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
		binder.setMessageCodesResolver(messageCodesResolver);
		binder.initBeanPropertyAccess();
		// binder.initDirectFieldAccess();

		// binder.setValidator(userValidator);
		binder.addValidators(userValidator);

	}

	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "about");
		modelAndView.addObject("pageTitle", "page.title.about");
		modelAndView.setViewName("about");
		return modelAndView;
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "home");
		modelAndView.addObject("pageTitle", "page.title.home");
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/")
	public ModelAndView home1() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "home");
		modelAndView.addObject("pageTitle", "page.title.home");
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "login");
		modelAndView.addObject("pageTitle", "page.title.login");
		modelAndView.setViewName("login");
		return modelAndView;
	}

}
