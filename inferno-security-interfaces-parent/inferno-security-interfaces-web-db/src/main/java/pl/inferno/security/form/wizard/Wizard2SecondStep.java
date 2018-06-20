/**
 *
 */
package pl.inferno.security.form.wizard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.service.RoleService;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.form.UserRoleWizardForm;

/**
 * Class WizardSecondStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/wizard2")
public class Wizard2SecondStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(Wizard2SecondStep.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.form.wizard.WizardStep#show()
	 */
	@RequestMapping(value = "/assign-role", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, ModelAndView mav) {
		LOGGER.debug("[WIZARD] [STEP 2] Roles selection and assigment page is showing...");

		mav.setViewName("wizard/role-assignment");
		mav.addObject("selectedTab", "steps");
		mav.addObject("step", "step2");

		if (request.getParameterNames().hasMoreElements()) {
			String param = request.getParameterNames().nextElement();
			LOGGER.debug("param ==> {}", param);
		}

		LOGGER.debug("form: {}", mav.getModelMap().get("formWizard"));

		String userId = request.getParameter("userId");
		LOGGER.debug("[WIZARD] userId: {}", userId);
		if (roleService == null) {
			LOGGER.error("RoleService is empty! {}", roleService);

		} else {
			List<Role> allRoles = roleService.findAllRoles();
			mav.addObject("roles", allRoles);
		}
		prepare();
		return mav;
	}

	@RequestMapping(value = "/assign-role", method = RequestMethod.POST, params = "selectedos")
	public ModelAndView apply(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId,
			ModelAndView mav) {
		UserRoles assigment = new UserRoles();
		User user;
		UserRoleWizardForm form = new UserRoleWizardForm();
		if (userId != null) {
			form = new UserRoleWizardForm();
			user = userService.findById(userId);
			form.setSelectedUser(user);
		}
		user = form.getSelectedUser();
		Role role = roleService.findRoleById(roleId);

		assigment.setAssignedRole(role);
		assigment.setAuthority(role.getAuthority());
		assigment.setUser(user);

		form.setAuthority(role.getAuthority());
		form.setRole(role);
		form.setRoleAssigment(assigment);
		form.setRoleName(role.getName());
		form.setStep(2);
		form.setSelectedUser(user);
		mav.addObject("formWizard", form);
		mav.addObject("pageName", "userRoles.assigment.wizard.step2.choose.roles.page");
		return mav;
	}

	private void prepare() {

	}
}