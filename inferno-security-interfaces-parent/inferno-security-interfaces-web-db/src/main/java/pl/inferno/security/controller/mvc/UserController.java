/**
 *
 */
package pl.inferno.security.controller.mvc;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.LocalDate;
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
import pl.inferno.security.core.model.Address;
import pl.inferno.security.core.model.AddressType;
import pl.inferno.security.core.model.Person;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.AddressService;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.form.AddressForm;
import pl.inferno.security.form.InfernoErrorObject;
import pl.inferno.security.form.SuccessfullAction;
import pl.inferno.security.form.SuccessfullAction.Change;
import pl.inferno.security.form.UserForm;
import pl.inferno.security.validator.AddressValidator;
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
	private AddressService addressService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private AddressValidator addressValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
		binder.setMessageCodesResolver(messageCodesResolver);
		binder.initBeanPropertyAccess();
		// binder.initDirectFieldAccess();

		if (UserForm.USER_FORM_OBJECT_NAME.equals(binder.getObjectName())) {
			// binder.setValidator(userValidator);
			binder.addValidators(userValidator);
		}
		if (AddressForm.ADDRESS_FORM_OBJECT_NAME.equals(binder.getObjectName())) {
			binder.addValidators(addressValidator);
		}
	}

	@GetMapping
	public ModelAndView user(Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getUserByUserName(principal.getName());

		UserForm userForm = new UserForm();
		Person person = user.getPerson();
		userForm.setAction(request.getParameter("action"));
		userForm.setUsername(user.getUsername());
		userForm.setOldPassword(user.getPassword());
		if (person != null) {
			userForm.setDateOfBirth(new LocalDate(person.getDateOfBirth()));
			userForm.setFirstName(person.getFirstName());
			userForm.setLastName(person.getLastName());
			userForm.setEmail(person.getEmail());
			userForm.setHomePhoneNumber(person.getHomePhoneNumber());
			userForm.setMobilePhoneNumber(person.getMobilePhoneNumber());
		}
		AddressForm addressForm = new AddressForm();

		addressForm.setAction(request.getParameter("action"));

		addressForm.setType(request.getParameter("type"));
		if (person != null) {
			modelAndView.addObject("addressTypes", recheckAddressTypes(person.getAddresses(), addressForm));
		}
		SuccessfullAction successfullAction = new SuccessfullAction();
		successfullAction.setSuccess(false);
		addressForm.setSuccessfullAction(successfullAction);

		modelAndView.addObject(AddressForm.ADDRESS_FORM_OBJECT_NAME, addressForm);

		modelAndView.addObject("action", userForm.getAction());
		userForm.setSuccessfullAction(successfullAction);
		modelAndView.addObject(UserForm.USER_FORM_OBJECT_NAME, userForm);

		modelAndView.addObject("page", "user");
		modelAndView.addObject("pageTitle", "page.title.user");
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user");

		return modelAndView;
	}

	@PostMapping
	public ModelAndView userAction(Principal principal, @ModelAttribute @Valid UserForm userForm,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response,
			ModelAndView modelAndView) {

		LOGGER.debug("ACTION: {} ", userForm.getAction());
		modelAndView.addObject("pageTitle", "page.title.user");
		User user = userService.getUserByUserName(principal.getName());
		Person person = user.getPerson();

		AddressForm addressForm = new AddressForm();
		List<Address> addresses = (person != null) && (person.getAddresses() != null) ? person.getAddresses()
				: new ArrayList<>();
		addressForm.setUsersDefinedAddresses(addresses);

		Map<AddressForm.AddressType.Type, Address> addressesMap = new HashMap<>();

		for (Address address : addresses) {
			if (AddressType.MAIN.equals(address.getType())) {
				addressesMap.put(AddressForm.AddressType.Type.MAIN, address);
				addressForm.setMainAddressDefined(true);
			} else if (AddressType.CORRESPONDENCE.equals(address.getType())) {
				addressesMap.put(AddressForm.AddressType.Type.CORRESPONDENCE, address);
				addressForm.setCorrespondenceAddressDefined(true);
			} else if (AddressType.ADDITIONAL.equals(address.getType())) {
				addressesMap.put(AddressForm.AddressType.Type.ADDITIONAL, address);
				addressForm.setAdditionalAddressDefined(true);
			}
		}
		addressForm.setUsersAddressesMap(addressesMap);
		addressForm.setAllowAdd(addresses.size() < 3);
		addressForm.setCountAddresses(addresses.size());

		Address main = addressesMap.get(AddressForm.AddressType.Type.MAIN);
		if (main != null) {
			addressForm.setId(main.getId());
			addressForm.setAppartment(main.getAppartment());
			addressForm.setBuildingNumber(main.getBuildingNumber());
			addressForm.setCity(main.getCity());
			addressForm.setDistrict(main.getDistrict());
			addressForm.setPostCode(main.getPostCode());
			addressForm.setStreet(main.getStreet());
			addressForm.setType(AddressForm.AddressType.Type.MAIN.name());
		}

		modelAndView.addObject(AddressForm.ADDRESS_FORM_OBJECT_NAME, addressForm);

		UserForm oldUserForm = new UserForm();
		oldUserForm.setAction(userForm.getAction());
		oldUserForm.setOldPassword(user.getPassword());
		oldUserForm.setUsername(user.getUsername());
		if (person != null) {
			oldUserForm.setDateOfBirth(new LocalDate(person.getDateOfBirth()));
			oldUserForm.setFirstName(person.getFirstName());
			oldUserForm.setLastName(person.getLastName());
			oldUserForm.setEmail(person.getEmail());
			oldUserForm.setHomePhoneNumber(person.getHomePhoneNumber());
			oldUserForm.setMobilePhoneNumber(person.getMobilePhoneNumber());
		}
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
			userForm.setErrors(errors);

			modelAndView.addObject("user", user);
			modelAndView.addObject(UserForm.USER_FORM_OBJECT_NAME, userForm);

			return modelAndView;
		}

		SuccessfullAction successfullAction = new SuccessfullAction();
		successfullAction.setObjectName(UserForm.USER_FORM_OBJECT_NAME);
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
			if ((oldUserForm.getFirstName() != null) && !oldUserForm.getFirstName().equals(userForm.getFirstName())) {
				person.setFirstName(userForm.getFirstName());

				change.setFieldName("firstName");
				change.setMessageCode("userForm.firstName.change.success");
				change.setOldValue(oldUserForm.getFirstName());
				change.setNewValue(person.getFirstName());
				changes.add(change);
			}

			if ((oldUserForm.getLastName() != null) && !oldUserForm.getLastName().equals(userForm.getLastName())) {
				person.setLastName(userForm.getLastName());

				change = successfullAction.new Change();
				change.setFieldName("lastName");
				change.setMessageCode("userForm.lastName.change.success");
				change.setOldValue(oldUserForm.getLastName());
				change.setNewValue(person.getLastName());
				changes.add(change);
			}

			if ((oldUserForm.getDateOfBirth() != null)
					&& !oldUserForm.getDateOfBirth().equals(userForm.getDateOfBirth())) {
				person.setDateOfBirth(new Date(userForm.getDateOfBirth().toDate().getTime()));

				change = successfullAction.new Change();
				change.setFieldName("dateOfBirth");
				change.setMessageCode("userForm.dateOfBirth.change.success");
				change.setOldValue(oldUserForm.getDateOfBirth());
				change.setNewValue(person.getDateOfBirth());
				changes.add(change);
			}
		}

		User userToSave = user;

		Person personToSave = person;
		if (person == null) {
			personToSave = new Person();
		}
		personToSave.setEmail(userForm.getEmail());
		personToSave.setHomePhoneNumber(userForm.getHomePhoneNumber());
		personToSave.setMobilePhoneNumber(userForm.getMobilePhoneNumber());
		userToSave.setPerson(personToSave);

		User savedUser = userService.saveUser(userToSave);
		if (savedUser != null) {
			successfullAction.setChanges(changes);
			successfullAction.setSuccess(true);
			userForm.setSuccessfullAction(successfullAction);
		}

		modelAndView.addObject(UserForm.USER_FORM_OBJECT_NAME, userForm);
		modelAndView.addObject("user", savedUser);
		return modelAndView;
	}

	@PostMapping("/address")
	public ModelAndView changeAddress(Principal principal, @ModelAttribute @Valid AddressForm addressForm,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response,
			ModelAndView modelAndView) {
		LOGGER.debug("Address Form: {}", addressForm);
		modelAndView.addObject("pageTitle", "page.title.user");
		User user = userService.getUserByUserName(principal.getName());
		Person person = user.getPerson();

		modelAndView.setViewName("user");
		modelAndView.addObject(UserForm.USER_FORM_OBJECT_NAME, new UserForm());

		if (bindingResult.hasErrors()) {
			List<InfernoErrorObject> errors = new ArrayList<>();
			for (ObjectError obj : bindingResult.getAllErrors()) {

				LOGGER.debug("obj error type: {}", obj.getClass().getName());
				InfernoErrorObject infernoError = objectErrorConverter.convert(obj);
				errors.add(infernoError);
				LOGGER.debug("converted error type: {}", infernoError.toString());
			}
			addressForm.setErrors(errors);
			modelAndView.addObject("user", user);
			modelAndView.addObject(AddressForm.ADDRESS_FORM_OBJECT_NAME, addressForm);

			return modelAndView;
		} else {
			addressForm.setAction(request.getParameter("action"));
			if (addressForm.getAction().equals(AddressForm.FormActions.Action.EDIT.getParam())) {
				Address address = addressService.findById(addressForm.getId());
				address.setId(addressForm.getId());
				address.setAppartment(addressForm.getAppartment());
				address.setBuildingNumber(addressForm.getBuildingNumber());
				address.setCity(addressForm.getCity());
				address.setDistrict(addressForm.getDistrict());
				address.setPostCode(addressForm.getPostCode());
				address.setStreet(addressForm.getStreet());
				address.setType(AddressType.valueOf(addressForm.getType()));

				List<Address> addresses = new ArrayList<>();
				for (Address addressPerson : person.getAddresses()) {
					if (addressPerson.getType().equals(address.getType())) {
						addresses.add(address);
					} else {
						addresses.add(addressPerson);
					}
				}
				person.setAddresses(addresses);
				user.setPerson(person);

				addressService.saveAddress(address);

			} else if (addressForm.getAction().equals(AddressForm.FormActions.Action.DELETE.getParam())) {
				LOGGER.debug("* * * DELETE * * *");

				Address address = addressService.deleteAddressById(addressForm.getId());
				if (address != null) {
					LOGGER.debug("DELETED ADDRESS: {}", address);

					SuccessfullAction success = logSuccess(AddressForm.ADDRESS_FORM_OBJECT_NAME, "address", address,
							null, "addressForm.address.deleted");

					addressForm.setSuccessfullAction(success);

					List<Address> addresses = person.getAddresses();
					if (addresses.contains(address)) {
						addresses.remove(address);
					}
					person.setAddresses(addresses);
					user.setPerson(person);
				} else {
					bindingResult.reject("addressForm.save.error");
					List<InfernoErrorObject> errors = new ArrayList<>();
					for (ObjectError obj : bindingResult.getAllErrors()) {

						LOGGER.debug("obj error type: {}", obj.getClass().getName());
						InfernoErrorObject infernoError = objectErrorConverter.convert(obj);
						errors.add(infernoError);
						LOGGER.debug("converted error type: {}", infernoError.toString());
					}
					addressForm.setErrors(errors);
					modelAndView.addObject(AddressForm.ADDRESS_FORM_OBJECT_NAME, addressForm);
					modelAndView.addObject("user", user);

					return modelAndView;
				}
			} else if (addressForm.getAction().equals(AddressForm.FormActions.Action.ADD.getParam())) {
				LOGGER.debug("* * * ADD * * *");

				Address address = new Address();
				address.setAppartment(addressForm.getAppartment());
				address.setBuildingNumber(addressForm.getBuildingNumber());
				address.setCity(addressForm.getCity());
				address.setDistrict(addressForm.getDistrict());
				address.setPostCode(addressForm.getPostCode());
				address.setStreet(addressForm.getStreet());
				address.setType(AddressType.valueOf(addressForm.getType()));
				address.setCreatedBy(user.getUsername());
				Address storedAddress = addressService.saveAddress(address);
				if (storedAddress != null) {
					LOGGER.debug("SUCCESS! Stored Entity: {}", storedAddress);

					SuccessfullAction success = logSuccess(AddressForm.ADDRESS_FORM_OBJECT_NAME, "address", null,
							storedAddress, "addressForm.new.address.added");

					addressForm.setSuccessfullAction(success);

					List<Address> addresses = person.getAddresses();
					if (!addresses.contains(storedAddress)) {
						addresses.add(storedAddress);
					}
					person.setAddresses(addresses);
					user.setPerson(person);
					userService.saveUser(user);
					modelAndView.addObject("addressTypes", recheckAddressTypes(person.getAddresses(), addressForm));
				} else {
					bindingResult.reject("addressForm.save.error");
					List<InfernoErrorObject> errors = new ArrayList<>();
					for (ObjectError obj : bindingResult.getAllErrors()) {

						LOGGER.debug("obj error type: {}", obj.getClass().getName());
						InfernoErrorObject infernoError = objectErrorConverter.convert(obj);
						errors.add(infernoError);
						LOGGER.debug("converted error type: {}", infernoError.toString());
					}
					addressForm.setErrors(errors);
					modelAndView.addObject(AddressForm.ADDRESS_FORM_OBJECT_NAME, addressForm);
					modelAndView.addObject("user", user);

					return modelAndView;
				}
			}
		}
		modelAndView.addObject(AddressForm.ADDRESS_FORM_OBJECT_NAME, addressForm);
		modelAndView.addObject("user", user);

		return modelAndView;
	}

	private List<AddressForm.AddressType.Type> recheckAddressTypes(List<Address> addresses, AddressForm addressForm) {

		for (Address definedAddress : addresses) {
			if (definedAddress.getType().equals(AddressType.MAIN)) {
				addressForm.setMainAddressDefined(true);
			} else {
				addressForm.setMainAddressDefined(false);
			}
			if (definedAddress.getType().equals(AddressType.CORRESPONDENCE)) {
				addressForm.setCorrespondenceAddressDefined(true);
			} else {
				addressForm.setCorrespondenceAddressDefined(false);
			}
			if (definedAddress.getType().equals(AddressType.ADDITIONAL)) {
				addressForm.setAdditionalAddressDefined(true);
			} else {
				addressForm.setAdditionalAddressDefined(false);
			}
		}

		if ((addresses != null) && !addresses.isEmpty()) {
			addressForm.setCountAddresses(addresses.size());
			addressForm.setAllowAdd(addresses.size() < 3);
		}

		List<AddressForm.AddressType.Type> typesList = new ArrayList<>();
		if (addressForm.isAllowAdd()) {
			if (!addressForm.isMainAddressDefined()) {
				typesList.add(AddressForm.AddressType.Type.MAIN);
			}
			if (!addressForm.isCorrespondenceAddressDefined()) {
				typesList.add(AddressForm.AddressType.Type.CORRESPONDENCE);
			}
			if (!addressForm.isAdditionalAddressDefined()) {
				typesList.add(AddressForm.AddressType.Type.ADDITIONAL);
			}
		}

		return typesList;
	}

	private SuccessfullAction logSuccess(String objectName, String fieldName, Object oldValue, Object newValue,
			String messageCode) {
		SuccessfullAction success = new SuccessfullAction();
		success.setSuccess(true);
		success.setObjectName(objectName);
		List<Change> changes = new ArrayList<>();
		SuccessfullAction.Change change = success.new Change();
		change.setFieldName(fieldName);
		change.setOldValue(oldValue);
		change.setNewValue(newValue);
		change.setMessageCode(messageCode);
		changes.add(change);
		success.setChanges(changes);

		return success;
	}

}
