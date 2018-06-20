/**
 *
 */
package pl.inferno.security.validator;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.errors.handler.Severity;
import pl.inferno.security.form.UserForm;
import pl.inferno.security.utils.MessageUtils;

/**
 * Class UserValidator
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Component
public class UserValidator implements Validator {

	private static Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return UserForm.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		LOGGER.debug("USERFORM AS TARGET: {}", userForm);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		User user = userService.getUserByUserName(currentPrincipalName);
		Person person = user.getPerson();

		UserForm oldUserForm = new UserForm();
		if (person != null) {
			oldUserForm.setDateOfBirth(new LocalDate(person.getDateOfBirth()));
			oldUserForm.setFirstName(person.getFirstName());
			oldUserForm.setLastName(person.getLastName());
			oldUserForm.setEmail(person.getEmail());
			oldUserForm.setHomePhoneNumber(person.getHomePhoneNumber());
			oldUserForm.setMobilePhoneNumber(person.getMobilePhoneNumber());
		}

		oldUserForm.setOldPassword(user.getPassword());
		oldUserForm.setUsername(user.getUsername());
		userForm.setOldForm(oldUserForm);

		if (userForm.getAction().equals(UserForm.FormActions.Action.CHANGE_PASSWORD.getParam())) {
			Map<String, Object> argumentsMap = new HashMap<>();
			argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
			argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.EMPTY);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "userForm.oldPassword.empty",
					new Object[] { argumentsMap });
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword1", "userForm.newPassword1.empty",
					new Object[] { argumentsMap });
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword2", "userForm.newPassword2.empty",
					new Object[] { argumentsMap });

			LOGGER.debug("ERRORS COUNT: {}", errors.getErrorCount());
			if (errors.getErrorCount() == 3) {
				LOGGER.debug("CURRENT SEVERITY: {}", argumentsMap.get(MessageUtils.SEVERITY_MAP_KEY));
				LOGGER.debug("CURRENT VALUE: {}", argumentsMap.get(MessageUtils.VALUE_MAP_KEY));
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.NO_CHANGES);
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.WARNING));
				LOGGER.debug("MODIFIED SEVERITY: {}", argumentsMap.get(MessageUtils.SEVERITY_MAP_KEY));
				LOGGER.debug("MODIFIED VALUE: {}", argumentsMap.get(MessageUtils.VALUE_MAP_KEY));

				// errors.reject("warn.noChanges", new Object[] { argumentsMap },
				// MessageUtils.NO_CHANGES);
				errors.rejectValue(null, "warn.noChanges", new Object[] { argumentsMap }, MessageUtils.NO_CHANGES);

				LOGGER.debug("CURRENT VALIDATION ERRORS: {}", errors);
				// ValidationUtils.rejectIfEmpty(errors, errors.getObjectName(),
				// "warn.noChanges",
				// new Object[] { argumentsMap }, MessageUtils.NO_CHANGES);

			}
			argumentsMap = new HashMap<>();
			argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.EMPTY);
			argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.EMPTY);

			if ((user != null) && !passwordEncoder.matches(userForm.getOldPassword(), user.getPassword())) {
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, userForm.getOldPassword());

				errors.rejectValue("oldPassword", "userForm.oldPassword.mismatch", new Object[] { argumentsMap }, null);

			}

			if (!userForm.getNewPassword1().equals(userForm.getNewPassword2())) {

				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, userForm.getNewPassword1());
				errors.rejectValue("newPassword1", "userForm.newPassword1.notEquals", new Object[] { argumentsMap },
						null);

				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, userForm.getNewPassword2());
				errors.rejectValue("newPassword2", "userForm.newPassword2.notEquals", new Object[] { argumentsMap },
						null);
			}

			if (passwordEncoder.encode(userForm.getNewPassword1()).equals(oldUserForm.getOldPassword())
					&& passwordEncoder.encode(userForm.getNewPassword2()).equals(oldUserForm.getOldPassword())) {
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.NO_CHANGES);
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.WARNING));
				errors.rejectValue(null, "warn.noChanges", new Object[] { argumentsMap }, MessageUtils.NO_CHANGES);
			}
		}

		if (userForm.getAction().equals(UserForm.FormActions.Action.EDIT_USER.getParam())) {
			Map<String, Object> argumentsMap = new HashMap<>();
			argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
			argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.EMPTY);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "userForm.firstName.empty",
					new Object[] { argumentsMap });
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "userForm.lastName.empty",
					new Object[] { argumentsMap });
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userForm.email.empty",
					new Object[] { argumentsMap });

			if (userForm.getDateOfBirth() == null) {
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.EMPTY);
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth",
				// "userForm.dateOfBirth.empty",
				// new Object[] { argumentsMap });
				errors.rejectValue("dateOfBirth", "userForm.dateOfBirth.empty", new Object[] { argumentsMap },
						"userForm.dateOfBirth.empty");
			}
			if ((errors.getErrorCount() == 4) || (userForm.getFirstName().equals(oldUserForm.getFirstName())
					&& userForm.getDateOfBirth().equals(oldUserForm.getDateOfBirth())
					&& userForm.getLastName().equals(oldUserForm.getLastName())
					&& userForm.getEmail().equalsIgnoreCase(oldUserForm.getEmail()))) {
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.WARNING));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.NO_CHANGES);
				errors.reject("warn.noChanges", new Object[] { argumentsMap }, null);
			}

		}

		LOGGER.debug("CURRENT (LAST) VALIDATION ERRORS: {}", errors);

	}

}
