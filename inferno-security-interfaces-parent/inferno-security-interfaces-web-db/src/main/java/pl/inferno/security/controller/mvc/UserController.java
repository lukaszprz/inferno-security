/**
 * 
 */
package pl.inferno.security.controller.mvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.converter.ObjectErrorToInfernoErrorObjectConverter;
import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.form.InfernoErrorObject;
import pl.inferno.security.form.SuccessfullAction;
import pl.inferno.security.form.SuccessfullAction.Change;
import pl.inferno.security.form.UserForm;
import pl.inferno.security.validator.UserValidator;

/**
 * Class UserController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ObjectErrorToInfernoErrorObjectConverter objectErrorConverter;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidator userValidator;

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

	@GetMapping
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

		modelAndView.addObject("page", "user");
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user");
		SuccessfullAction successfullAction = new SuccessfullAction();
		successfullAction.setSuccess(false);
		modelAndView.addObject("successAction", successfullAction);
		// return "/user";
		return modelAndView;
	}

	@PostMapping
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
