/**
 *
 */
package pl.inferno.security.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lukasz-adm
 *
 */
@Controller
public class WebController {

	@RequestMapping("/")
	public String homeRoot() {
		return "index";
	}

}
