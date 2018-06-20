/**
 *
 */
package pl.inferno.security.form.wizard;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class WizardStep
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public abstract class WizardStep {

	private static final Logger LOGGER = LoggerFactory.getLogger(WizardStep.class);

	protected ModelAndView mav = null;

	private WizardStep next = null;

	public abstract ModelAndView show(HttpServletRequest request);

	/**
	 * @return the next
	 */
	public WizardStep getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(WizardStep next) {
		this.next = next;
	}
}
