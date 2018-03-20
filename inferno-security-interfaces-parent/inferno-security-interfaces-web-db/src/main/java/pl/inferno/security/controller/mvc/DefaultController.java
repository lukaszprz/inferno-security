/**
 * Project: inferno-security-interfaces-web-db
 * File: DefaultController.java
 * Package: pl.inferno.security.controller.mvc
 * Location:
 * 9 mar 2018 07:31:03 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.controller.mvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.inferno.security.converter.ObjectErrorToInfernoErrorObjectConverter;
import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.errors.handler.Severity;
import pl.inferno.security.form.InfernoErrorObject;

import pl.inferno.security.form.SuccessfullAction;
import pl.inferno.security.form.UserForm;
import pl.inferno.security.form.SuccessfullAction.Change;
import pl.inferno.security.utils.MessageUtils;
import pl.inferno.security.validator.UserValidator;

/**
 * Class DefaultController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Controller
public class DefaultController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

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

	// @Bean
	// UserValidator userValidator() {
	//
	// return new UserValidator(passwordEncoder, userService);
	// }

	@InitBinder("userForm")
	protected void initBinder(WebDataBinder binder) {

		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
		binder.setMessageCodesResolver(messageCodesResolver);
		// binder.initBeanPropertyAccess();
		binder.initDirectFieldAccess();

		// binder.setValidator(userValidator());
		binder.addValidators(userValidator);

	}

	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "about");
		modelAndView.setViewName("about");
		return modelAndView;
	}

	@GetMapping("/admin")
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> allUsers = userService.getAllUsers();
		modelAndView.addObject("allUsers", allUsers);
		modelAndView.addObject("selectedUsers", "");
		modelAndView.addObject("page", "admin");
		modelAndView.setViewName("admin");
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
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/")
	public ModelAndView home1() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "home");
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "login");
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping("/user")
	public ModelAndView user(Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getUserByUserName(principal.getName());

		UserForm userForm = new UserForm();
		Person person = user.getPerson();
		userForm.setDateOfBirth(person.getDateOfBirth());
		userForm.setFirstName(person.getFirstName());
		userForm.setLastName(person.getLastName());
		userForm.setOldPassword(user.getPassword());
		userForm.setAction(request.getParameter("action"));
		userForm.setUsername(user.getUsername());

		modelAndView.addObject("action", userForm.getAction());
		modelAndView.addObject("userForm", userForm);
		modelAndView.addObject("oldUserForm", userForm);
		request.getSession(true).setAttribute("oldUserForm", userForm);
		modelAndView.addObject("page", "user");
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user");
		SuccessfullAction successfullAction = new SuccessfullAction();
		successfullAction.setSuccess(false);
		modelAndView.addObject("successAction", successfullAction);
		// return "/user";
		return modelAndView;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView userAction(Principal principal, @ModelAttribute @Valid UserForm userForm,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response,
			ModelAndView modelAndView) {

		LOGGER.debug("ACTION: {} ", userForm.getAction());
		User user = userService.getUserByUserName(principal.getName());
		Person person = user.getPerson();

		UserForm oldUserForm = new UserForm();
		oldUserForm.setAction(userForm.getAction());
		oldUserForm.setDateOfBirth(person.getDateOfBirth());
		oldUserForm.setFirstName(person.getFirstName());
		oldUserForm.setLastName(person.getLastName());
		oldUserForm.setOldPassword(user.getPassword());
		oldUserForm.setUsername(user.getUsername());
		userForm.setOldForm(oldUserForm);

		LOGGER.debug("NEW USER-FORM OBJECT: {}", userForm.toString());
		LOGGER.debug("OLD USER-FORM OBJECT: {}", oldUserForm.toString());

		modelAndView.setViewName("user");

		if (bindingResult.hasErrors()) {
			List<InfernoErrorObject> errors = new ArrayList<>();
			for (ObjectError obj : bindingResult.getAllErrors()) {

				LOGGER.debug("obj error type: {}", obj.getClass().getName());
				InfernoErrorObject infernoError = objectErrorConverter.convert(obj);
				errors.add(infernoError);
				LOGGER.debug("converted error type: {}", infernoError.toString());
			}

			modelAndView.addObject("errors", errors);
			modelAndView.addObject("user", user);
			modelAndView.addObject("userForm", userForm);

			return modelAndView;
		}

		SuccessfullAction successfullAction = new SuccessfullAction();
		successfullAction.setObjectName("userForm");
		List<Change> changes = new ArrayList<>();
		if (userForm.getAction().equals(UserForm.FormActions.Action.CHANGE_PASSWORD.getParam())) {

			user.setNewPassword(passwordEncoder.encode(userForm.getNewPassword2()));
			SuccessfullAction.Change change = successfullAction.new Change();
			change.setFieldName("newPassword2");
			change.setMessageCode("userForm.newPassword.change.success");
			change.setOldValue(oldUserForm.getOldPassword());
			change.setNewValue(user.getNewPassword());
			changes.add(change);
		}

		if (userForm.getAction().equals(UserForm.FormActions.Action.EDIT_USER.getParam())) {
			SuccessfullAction.Change change = successfullAction.new Change();
			if (!oldUserForm.getFirstName().equals(userForm.getFirstName())) {
				person.setFirstName(userForm.getFirstName());

				change.setFieldName("firstName");
				change.setMessageCode("userForm.firstName.change.success");
				change.setOldValue(oldUserForm.getFirstName());
				change.setNewValue(person.getFirstName());
				changes.add(change);
			}

			if (!oldUserForm.getLastName().equals(userForm.getLastName())) {
				person.setLastName(userForm.getLastName());

				change = successfullAction.new Change();
				change.setFieldName("lastName");
				change.setMessageCode("userForm.lastName.change.success");
				change.setOldValue(oldUserForm.getLastName());
				change.setNewValue(person.getLastName());
				changes.add(change);
			}

			if (!oldUserForm.getDateOfBirth().equals(userForm.getDateOfBirth())) {
				person.setDateOfBirth(userForm.getDateOfBirth());

				change = successfullAction.new Change();
				change.setFieldName("dateOfBirth");
				change.setMessageCode("userForm.dateOfBirth.change.success");
				change.setOldValue(oldUserForm.getDateOfBirth());
				change.setNewValue(person.getDateOfBirth());
				changes.add(change);
			}
		}

		User savedUser = userService.saveUser(user);
		if (savedUser != null) {
			successfullAction.setChanges(changes);
			successfullAction.setSuccess(true);
			modelAndView.addObject("successAction", successfullAction);
		}
		modelAndView.addObject("userForm", userForm);
		modelAndView.addObject("user", savedUser);
		return modelAndView;
	}

}
