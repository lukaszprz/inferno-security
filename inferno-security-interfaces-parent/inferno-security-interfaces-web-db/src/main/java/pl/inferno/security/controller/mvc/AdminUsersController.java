/**
 * 
 */
package pl.inferno.security.controller.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
		modelAndView.addObject("user", new User());
		modelAndView.getModelMap().addAttribute("user", new User());
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	@GetMapping("/user/create")
	public ModelAndView showUserCreatePage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.getModelMap().addAttribute("user", user);
		modelAndView.setViewName("admin_user_entity");
		return modelAndView;
	}

	@PostMapping("/user/create")
	public ModelAndView createUser(ModelAndView modelAndView, @ModelAttribute @Valid User user,
			BindingResult bindingResult, HttpServletRequest request) {
		LOGGER.debug("INCOMMING USER: {}", user);
		User userExists = userService.getUserByUserName(user.getUsername());
		modelAndView.getModelMap().addAttribute("user", user);
		modelAndView.setViewName("admin_user_entity");
		return modelAndView;
	}
}
