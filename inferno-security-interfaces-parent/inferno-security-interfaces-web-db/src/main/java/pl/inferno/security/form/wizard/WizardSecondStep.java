/**
 *
 */
package pl.inferno.security.form.wizard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
 * Class WizardSecondStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/wizard")
public class WizardSecondStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(WizardSecondStep.class);

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
	@RequestMapping(value = "/assign-role", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, ModelAndView mav, @RequestParam("userId") Long userId,
			@ModelAttribute(name = "formWizard") UserRoleWizardForm form) {
		LOGGER.debug("[WIZARD] [STEP 2] Roles selection and assigment page is showing...");
		LOGGER.debug("form: {}", form);

		mav = prepare(mav);

		mav.setViewName("wizard/role-assignment");
		mav.addObject("step", "step2");

		User user = userService.findById(userId);

		List<UserRoles> userRoles = assigmentservice.findAllAssignedRoles(user);
		List<Role> allRoles = roleService.findAllRoles();
		List<String> assignedRoles = new ArrayList<>();
		List<Role> availableRoles = new ArrayList<>();
		List<Role> selectedRoles = new ArrayList<>();
		List<UserRoles> selectedAssigments = userRoles;

		for (UserRoles userRole : userRoles) {
			Role assignedRole = userRole.getAssignedRole();
			assignedRoles.add(assignedRole.getName());
		}

		for (Role role : allRoles) {
			if (!assignedRoles.contains(role.getName())) {
				availableRoles.add(role);
				UserRoles assigment = new UserRoles();
				assigment.setAssignedRole(role);
				selectedAssigments.add(assigment);
			}

		}

		mav.addObject("roles", availableRoles);
		mav.addObject("selectedRoles", selectedRoles);
		mav.addObject("userId", user.getId());
		mav.addObject("showNextButton", false);

		form.setStep(2);
		form.setSelectedUser(user);
		form.setAllRoles(allRoles);
		form.setAssignedRoles(selectedAssigments);
		mav.addObject("formWizard", form);

		return mav;
	}

	@RequestMapping(value = "/assign-role", method = RequestMethod.POST)
	public ModelAndView apply(@RequestParam("userId") Long userId, ModelAndView mav,
			@RequestParam("selectedRoles") List<String> selectedRoles,
			@ModelAttribute(name = "formWizard") UserRoleWizardForm form) {
		LOGGER.debug("[WIZARD] [STEP 2] Roles selection and assigment processing...");
		LOGGER.debug("form: {}", form);
		mav = prepare(mav);
		mav.setViewName("wizard/role-assignment");
		mav.addObject("step", 2);

		User user;
		// UserRoleWizardForm form = new UserRoleWizardForm();
		if (userId != null) {
			// form = new UserRoleWizardForm();
			user = userService.findById(userId);
			form.setSelectedUser(user);
		}
		user = form.getSelectedUser();
		List<Role> newRoles = new ArrayList<>();
		List<UserRoles> assigments = new ArrayList<>();
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
			}
		}
		mav.addObject("roles", newRoles);
		mav.addObject("showNextButton", true);

		form.setAssignedRoles(assigments);
		form.setStep(2);
		form.setSelectedUser(user);
		mav.addObject("formWizard", form);
		mav.addObject("step", 3);
		mav.addObject("pageName", "userRoles.assigment.wizard.step2.choose.roles.page");
		mav.addObject("userId", user.getId());
		mav.addObject("selectedRoles", selectedRoles);
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
}