/**
 * 
 */
package pl.inferno.security.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;
import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.errors.handler.Severity;
import pl.inferno.security.form.AddressForm;
import pl.inferno.security.utils.MessageUtils;

/**
 * Class AddressValidator
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Component
public class AddressValidator implements Validator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressValidator.class);

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return AddressForm.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		AddressForm addressForm = (AddressForm) target;
		LOGGER.debug("ADDRESSFORM AS TARGET: {}", addressForm);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		User user = userService.getUserByUserName(currentPrincipalName);
		Person person = user.getPerson();

		Map<String, Object> argumentsMap = new HashMap<>();
		argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.INFO));
		argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.EMPTY);

		LOGGER.info("PERFORMING ADDRESS VALIDATION FOR ACTION: {}", addressForm.getAction());
		if (addressForm.getAction().equals(AddressForm.FormActions.Action.EDIT.name())) {
			LOGGER.info("NOTHING TO VALIDATE");
		}

		if (addressForm.getAction().equals(AddressForm.FormActions.Action.DELETE.getParam())) {
			argumentsMap = new HashMap<>();
			argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
			argumentsMap.put(MessageUtils.VALUE_MAP_KEY, MessageUtils.EMPTY);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "addressForm.id.null",
					new Object[] { argumentsMap });
		}

		if (addressForm.getAction().equals(AddressForm.FormActions.Action.ADD.getParam())) {
			List<Address> addresses = person.getAddresses();
			if ((addresses != null) && (addresses.size() == 3)) {
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, 3);
				errors.reject("address.validation.errors.add.notAllowed", new Object[] { argumentsMap },
						"address.validation.errors.add.notAllowed");
			}
			boolean isMainAddressDefined = false;
			for (Address address : addresses) {
				if (address.getType().equals(AddressType.MAIN)) {
					isMainAddressDefined = true;
				}
			}
			if (addressForm.getType().equals(AddressForm.AddressType.Type.MAIN.name()) && isMainAddressDefined) {
				argumentsMap = new HashMap<>();
				argumentsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
				argumentsMap.put(MessageUtils.VALUE_MAP_KEY, "address.validation.errors.main.alreadyDefined");
				errors.reject("address.validation.errors.main.alreadyDefined", new Object[] { argumentsMap },
						"address.validation.errors.main.alreadyDefined");
			}
		}
	}
}
