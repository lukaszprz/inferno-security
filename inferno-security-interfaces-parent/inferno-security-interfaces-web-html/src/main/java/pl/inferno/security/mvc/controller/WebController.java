/**
 *
 */
package pl.inferno.security.mvc.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.inferno.security.web.service.UserService;

/**
 * @author lukasz-adm
 */
@Controller
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

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
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal, ModelAndView modelAndView) {
        // mapper.convertValue(UserDTO.class, UserDetails.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails = auth.getPrincipal();
        org.springframework.security.core.userdetails.UserDetails user = userService.loadUserByUsername(principal.getName());
        Map<String, Object> mapa = mapper.convertValue(auth, HashMap.class);
        for (String key : mapa.keySet()) {
            // LOGGER.debug("map key: {}", key);
            modelAndView.addObject(key, mapa.get(key));
            LOGGER.debug("{} ==> {}", key, mapa.get(key));
        }
        modelAndView.addObject("user", mapa);
        modelAndView.addObject("userDetails", userDetails);
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
