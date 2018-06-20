/**
 *
 */
package pl.inferno.security.form.wizard;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.form.SidePanel;
import pl.inferno.security.form.UserRoleWizardForm;

/**
 * Class WizardInitialStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/wizard2")
public class Wizard2InitialStep extends WizardStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(Wizard2InitialStep.class);

	/**
	 *
	 */
	public Wizard2InitialStep() {

		mav = new ModelAndView();
		mav.setViewName("wizard/init");
		mav.addObject("selectedTab", "steps");
		mav.addObject("step", "init");
		// String id = request.getParameter("id");
		// String entity = request.getParameter("entity");
		// String action = request.getParameter("action");

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
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.form.wizard.WizardStep#show()
	 */

	@RequestMapping("/init")
	@Override
	public ModelAndView show(HttpServletRequest request) {
		LOGGER.debug("[WIZARD] [INIT] Initiating wizard...");

		mav.addObject("wizardForm", new UserRoleWizardForm());
		prepare();
		return mav;
	}

	private void prepare() {
		mav.addObject("pageName", "userRoles.assigment.wizard.step1.choose.users.page");
	}
}
