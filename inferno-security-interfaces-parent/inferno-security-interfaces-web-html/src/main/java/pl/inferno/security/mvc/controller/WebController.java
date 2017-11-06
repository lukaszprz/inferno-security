/**
 *
 */
package pl.inferno.security.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lukasz-adm
 *
 */
@Controller
public class WebController {

	@GetMapping(value = "/")
	public String homepage() {
		return "index";
	}

}
