/**
 *
 */
package pl.inferno.security.form.wizard;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.service.RoleService;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.core.service.impl.UserRoleAssigmentService;
import pl.inferno.security.form.SidePanel;
import pl.inferno.security.form.UserRoleWizardForm;

/**
 * Class WizardThirdStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@SessionAttributes("formWizard")
@RequestMapping("/wizard")
public class WizardThirdStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(WizardThirdStep.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleAssigmentService assigmentservice;

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.form.wizard.WizardStep#show()
	 */
	@RequestMapping(value = "/assignment-settings", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, ModelAndView mav,
			@RequestParam(name = "userId", required = false) Long userId,
			@RequestParam(name = "selectedRoles", required = false) List<String> selectedRoles,
			@ModelAttribute(name = "formWizard") UserRoleWizardForm form) {
		LOGGER.debug("[WIZARD] [STEP 3] Assigment final settings page is showing...");
		LOGGER.debug("selectedRoles: {}", selectedRoles);
		LOGGER.debug("form: {}", form);
		// UserRoleWizardForm form = new UserRoleWizardForm();
		form.setStep(3);
		mav = prepare(mav);
		mav.setViewName("wizard/assignment-settings");
		mav.addObject("step", "step3");
		mav.addObject("showNextButton", false);

		User user;

		if (userId == null) {
			userId = Long.parseLong(request.getParameter("userId"));

		}
		user = userService.findById(userId);
		form.setSelectedUser(user);
		user = form.getSelectedUser();
		List<Role> newRoles = new ArrayList<>();
		Set<UserRoles> assigments = user.getRoles();
		List<UserRoles> assigmentsList = form.getAssignedRoles();
		for (String select : selectedRoles) {

			LOGGER.debug("selected: {}", select);
			Role role = roleService.findRoleByName(select);
			if (role != null) {
				newRoles.add(role);
				UserRoles assigment = new UserRoles();
				assigment.setAssignedRole(role);
				assigment.setUser(user);
				assigment.setAuthority(role.getAuthority());
				assigments.add(assigment);
				assigmentsList.add(assigment);
			}
		}
		user.setRoles(assigments);
		form.setAssignedRoles(assigmentsList);
		form.setSelectedUser(user);
		mav.addObject("assignments", assigments);
		mav.addObject("role", new UserRoles());
		mav.addObject("formWizard", form);
		mav.addObject("userId", userId);
		mav.addObject("selectedRoles", selectedRoles);
		return mav;
	}

	@RequestMapping(value = "/assignment-settings", method = RequestMethod.POST)
	public ModelAndView apply(@ModelAttribute(name = "formWizard") UserRoleWizardForm form, ModelAndView mav, Principal principal) {
		LOGGER.debug("[WIZARD] [STEP 3] Assigment final settings processing...");
		form.setStep(3);
		mav.addObject("showNextButton", true);
		List<UserRoles> assigments = form.getAssignedRoles();
//		assigments.add(form.getRoleAssigment());
		User selectedUser = form.getSelectedUser();		
		if (selectedUser != null) {
			LOGGER.debug("Selected user: {}. {}", selectedUser.getId(), selectedUser.getUsername());
		} else {
			LOGGER.warn("No selected user in the form!");
		}
		
		if (assigments != null && !assigments.isEmpty()) {
			for (UserRoles assigment : assigments) {
				DateTime validFrom = DateTime.now();				
				DateTime validTo = null;
				if (assigment.getValidFrom() != null)
					validFrom = new DateTime(assigment.getValidFrom());
				if (assigment.getValidTo() != null)
					validTo = new DateTime(assigment.getValidTo());
				LOGGER.debug("Assigned role: (user: {}) - {}", assigment.getUser().getUsername(), assigment.getAssignedRole().getName());
				assigmentservice.assignRole(principal, selectedUser, assigment.getAssignedRole(), validFrom, validTo, assigment.getAssignedRole().getAuthority());								
			}
		} else {
			LOGGER.warn("No assigned roles for user in the form!");
		}
		
		
		mav.addObject("formWizard", form);
		mav = prepare(mav);
		return mav;
	}

	private ModelAndView prepare(ModelAndView mav) {

		mav.addObject("page", "admin");
		mav.addObject("pageTitle", "page.title.admin");
		SidePanel sidePanelTop = new SidePanel();
		sidePanelTop.setId(1);
		sidePanelTop.setTitle("page.header.admin");
		sidePanelTop.setContent(SidePanel.Panel.ADMIN1);
		sidePanelTop.setVisible(true);
		mav.addObject("sidePanelTop", sidePanelTop);

		SidePanel sidePanelBottom = new SidePanel();
		sidePanelBottom.setId(2);
		sidePanelBottom.setTitle("page.header.admin.entity.user_roles");
		sidePanelBottom.setContent(SidePanel.Panel.ADMIN2);
		sidePanelBottom.setVisible(true);
		mav.addObject("sidePanelBottom", sidePanelBottom);

		mav.addObject("entity", "user-roles");
		mav.addObject("action", "wizard");
		mav.addObject("pageName", "userRoles.assigment.wizard.step1.choose.users.page");
		return mav;
	}
	
	@ModelAttribute("formWizard")
	public UserRoleWizardForm getFormWizard(HttpServletRequest request) {
		return new UserRoleWizardForm();
	}
}
