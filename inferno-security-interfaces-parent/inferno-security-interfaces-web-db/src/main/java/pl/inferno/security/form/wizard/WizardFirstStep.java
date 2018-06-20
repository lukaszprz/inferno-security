/**
 *
 */
package pl.inferno.security.form.wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.service.RoleService;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.form.SidePanel;
import pl.inferno.security.form.UserRoleWizardForm;

/**
 * Class WizardFirstStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/wizard")
public class WizardFirstStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(WizardFirstStep.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.form.wizard.WizardStep#show()
	 */
	@RequestMapping(value = "/choose-user", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, ModelAndView mav) {
		LOGGER.debug("[WIZARD] [STEP 1] Users selection page is showing...");
		UserRoleWizardForm form = new UserRoleWizardForm();
		form.setStep(1);
		mav = prepare(mav);
		mav.setViewName("wizard/user-selection");
		
		form.setSelectedUser(new User());

		List<User> allUsers = userService.getAllUsers();
//		mav.addObject("users", allUsers);
//		mav.addObject("userId", 0L);

		form.setAllUsers(allUsers);
		LOGGER.debug("form: (NEW) {}", form);
		mav.addObject("formWizard", form);

		mav.addObject("showNextButton", false);

		return mav;
	}

	@RequestMapping(value = "/choose-user", method = RequestMethod.POST)
	public ModelAndView apply(@RequestParam(name = "selectedUserId") Long userId, ModelAndView mav,
			@ModelAttribute(name = "formWizard") UserRoleWizardForm form) {
		LOGGER.debug("[WIZARD] [STEP 1] User with ID {} is processed.", userId);
		LOGGER.debug("form: {}", form);
		mav = prepare(mav);
		mav.setViewName("wizard/user-selection");

		mav.addObject("step", 1);

		if (userId == null) {
			LOGGER.debug("Empty id");
			mav.addObject("errors", "ID cannot be empty");

			return mav;

		}
		User user = userService.findById(userId);
		Set<UserRoles> roles = user.getRoles();
		List<UserRoles> assigments = new ArrayList<>();
		for (UserRoles userRole : roles) {
			assigments.add(userRole);
		}
		List<User> allUsers = new ArrayList<>();
		allUsers.add(user);

		mav.addObject("users", allUsers);
		mav.addObject("showNextButton", true);
		mav.addObject("userId", user.getId());

		form.setSelectedUser(user);
		form.setAssignedRoles(assigments);

		mav.addObject("formWizard", form);

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
