/**
 *
 */
package pl.inferno.security.controller.mvc;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.form.SidePanel;

/**
 * Class AdminUserRolesController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/admin/user-roles")
public class AdminUserRolesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserRolesController.class);

	@GetMapping
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		String id = request.getParameter("id");
		String entity = request.getParameter("entity");
		String action = request.getParameter("action");

		modelAndView.addObject("page", "admin");
		modelAndView.addObject("pageTitle", "page.title.admin");
		SidePanel sidePanelTop = new SidePanel();
		sidePanelTop.setId(1);
		sidePanelTop.setTitle("page.header.admin");
		sidePanelTop.setContent(SidePanel.Panel.ADMIN1);
		sidePanelTop.setVisible(true);
		modelAndView.addObject("sidePanelTop", sidePanelTop);

		SidePanel sidePanelBottom = new SidePanel();
		sidePanelBottom.setId(2);
		sidePanelBottom.setTitle("page.header.admin.entity.user_roles");
		sidePanelBottom.setContent(SidePanel.Panel.ADMIN2);
		sidePanelBottom.setVisible(true);
		modelAndView.addObject("sidePanelBottom", sidePanelBottom);
		modelAndView.setViewName("user_role");

		modelAndView.addObject("entity", entity);
		modelAndView.addObject("action", action);
		return modelAndView;
	}
}
