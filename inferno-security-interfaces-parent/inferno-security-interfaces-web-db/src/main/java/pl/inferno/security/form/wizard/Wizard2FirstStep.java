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
@RequestMapping("/wizard2")
public class Wizard2FirstStep extends WizardStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(Wizard2FirstStep.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	private HttpServletRequest request;

	/**
	 *
	 */
	public Wizard2FirstStep() {
		mav = new ModelAndView();
		mav.setViewName("wizard/user-selection");
		mav.addObject("selectedTab", "steps");
		mav.addObject("step", "step1");

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

		// setNext(new WizardSecondStep());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.form.wizard.WizardStep#show()
	 */
	@RequestMapping(value = "/choose-user", method = RequestMethod.GET)
	@Override
	public ModelAndView show(HttpServletRequest request) {
		LOGGER.debug("[WIZARD] [STEP 1] Users selection page is showing...");
		List<User> allUsers = userService.getAllUsers();
		mav.addObject("users", allUsers);
		prepare();
		this.request = request;
		return mav;
	}

	@RequestMapping(value = "/choose-user", method = RequestMethod.POST)
	public ModelAndView apply(@RequestParam("userId") Long id) {
		LOGGER.debug("[WIZARD] [STEP 1] User with ID {} is processed.", id);
		if (id == null) {
			LOGGER.debug("Empty id");
			mav.addObject("errors", "ID cannot be empty");
			prepare();

			return mav;

		}
		User user = userService.findById(id);
		UserRoles assigment = new UserRoles();
		assigment.setUser(user);
		mav.addObject("assigment", assigment);
		mav.addObject("allRoles", roleService.findAllRoles());

		UserRoleWizardForm form = new UserRoleWizardForm();
		form.setSelectedUser(user);
		form.setStep(1);
		mav.addObject("formWizard", form);

		prepare();

		return getNext().show(request);
	}

	private void prepare() {
		mav.addObject("pageName", "userRoles.assigment.wizard.step1.choose.users.page");

	}
}
