/**
 *
 */
package pl.inferno.security.form.wizard;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.core.model.UserRoles;
import pl.inferno.security.core.service.impl.UserRoleAssigmentService;
import pl.inferno.security.form.UserRoleWizardForm;

/**
 * Class SummaryStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/wizard")
public class SummaryStep extends WizardStep {

	private HttpServletRequest request;

	@Autowired
	private UserRoleAssigmentService assigmentService;

	/**
	 *
	 */
	public SummaryStep() {
		mav = new ModelAndView();
		mav.setViewName("wizard/summary");
		mav.addObject("selectedTab", "steps");
		mav.addObject("step", "step4");
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SummaryStep.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.inferno.security.form.wizard.WizardStep#show()
	 */

	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	@Override
	public ModelAndView show(HttpServletRequest request) {
		LOGGER.debug("[WIZARD] Summary page is showing...");

		prepare();
		return mav;
	}

	@RequestMapping(value = "/summary", method = RequestMethod.POST, params = "finished")
	public ModelAndView finished(@ModelAttribute @Valid UserRoleWizardForm form, BindingResult bindingResult,
			Principal principal) {
		LOGGER.debug("[WIZARD] Finished.");
		UserRoles assigment = form.getRoleAssigment();
		UserRoles savedAssigment = assigmentService.save(principal, assigment);
		if (savedAssigment != null) {
			mav.addObject("result", "Role " + savedAssigment.getAuthority() + " has been successfuly assigned to user "
					+ savedAssigment.getUser().getUsername() + " with new ID: " + savedAssigment.getId());

		}

		prepare();
		return mav;
	}

	private void prepare() {
		mav.addObject("pageName", "userRoles.assigment.wizard.summary.page");
	}
}
