/**
 * Project: inferno-security-interfaces-web-db
 * File: DefaultController.java
 * Package: pl.inferno.security.controller.mvc
 * Location:
 * 9 mar 2018 07:31:03 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.controller.mvc;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;

/**
 * Class DefaultController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Controller
public class DefaultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> allUsers = userService.getAllUsers();
        modelAndView.addObject("allUsers", allUsers);
        modelAndView.addObject("selectedUsers", "");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal, ModelAndView modelAndView) {
        User user = userService.getUserByUserName(principal.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user");
        // return "/user";
        return modelAndView;
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
