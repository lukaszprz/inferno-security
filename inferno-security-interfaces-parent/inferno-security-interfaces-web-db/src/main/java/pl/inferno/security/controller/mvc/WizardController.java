/**
 *
 */
package pl.inferno.security.controller.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pl.inferno.security.form.WizardForm;

/**
 * Class WizardController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/wizard.form")
@SessionAttributes("command")
public class WizardController {

	/**
	 * The default handler (page=0)
	 */
	@RequestMapping
	public String getInitialPage(final ModelMap modelMap) {
		// put your initial command
		modelMap.addAttribute("command", new WizardForm());
		// populate the model Map as needed
		return "initialView";
	}

	/**
	 * First step handler (if you want to map each step individually to a method).
	 * You should probably either use this approach or the one below (mapping all
	 * pages to the same method and getting the page number as parameter).
	 */
	@RequestMapping(params = "_step=1")
	public String processFirstStep(final @ModelAttribute("command") WizardForm command, final Errors errors) {
		// do something with command, errors, request, response,
		// model map or whatever you include among the method
		// parameters. See the documentation for @RequestMapping
		// to get the full picture.
		return "firstStepView";
	}

	/**
	 * Maybe you want to be provided with the _page parameter (in order to map the
	 * same method for all), as you have in AbstractWizardFormController.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processPage(@RequestParam("_page") final int currentPage,
			final @ModelAttribute("command") WizardForm command, final HttpServletResponse response) {
		// do something based on page number
		// return pageViews[currentPage];
		return "cuurentStep";
	}

	/**
	 * The successful finish step ('_finish' request param must be present)
	 */
	@RequestMapping(params = "_finish")
	public String processFinish(final @ModelAttribute("command") WizardForm command, final Errors errors,
			final ModelMap modelMap, final SessionStatus status) {
		// some stuff
		status.setComplete();
		return "successView";
	}

	@RequestMapping(params = "_cancel")
	public String processCancel(final HttpServletRequest request, final HttpServletResponse response,
			final SessionStatus status) {
		status.setComplete();
		return "canceledView";
	}

}
