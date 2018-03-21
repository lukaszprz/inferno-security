/**
 * 
 */
package pl.inferno.security.controller.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;

/**
 * Class AdminController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> allUsers = userService.getAllUsers();
		modelAndView.addObject("allUsers", allUsers);
		modelAndView.addObject("selectedUsers", "");
		modelAndView.addObject("page", "admin");
		modelAndView.setViewName("admin");
		return modelAndView;
	}
}
