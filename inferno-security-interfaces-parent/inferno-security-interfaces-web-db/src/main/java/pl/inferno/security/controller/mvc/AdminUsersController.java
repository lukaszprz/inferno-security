/**
 *
 */
package pl.inferno.security.controller.mvc;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.form.AdminAction;
import pl.inferno.security.form.AdminUserForm;
import pl.inferno.security.form.SidePanel;

/**
 * Class AdminController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminUsersController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

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
		sidePanelBottom.setTitle("page.header.admin.entity.users");
		sidePanelBottom.setContent(SidePanel.Panel.ADMIN2);
		sidePanelBottom.setVisible(true);
		modelAndView.addObject("sidePanelBottom", sidePanelBottom);

		String id = request.getParameter("id");
		String entity = request.getParameter("entity");
		String action = request.getParameter("action");

		LOGGER.debug("[GET] ACTION: {} FOR ENTITY: {} WITH ID: {}", action, entity, id);
		AdminAction adminAction = AdminAction.fromParam(action);
		switch (adminAction) {
		case MAIN:
			break;
		case LIST:
			modelAndView = listAllUsers(modelAndView);
			break;
		case CREATE:
			modelAndView = prepareCreateUserPage(modelAndView);
			break;
		case EDIT:
			modelAndView = prepareEditUserPage(modelAndView, id);
			request.setAttribute("id", id);
			break;
		case REMOVE:
			modelAndView = removeUser(modelAndView, id);
			break;
		case DETAILS:
			modelAndView = prepareUserDetailsPage(modelAndView, id);
			modelAndView.addObject("sidePanelBottom", sidePanelBottom);
			break;

		default:
			ObjectError error = new ObjectError("user", "page.action.undefined");
			modelAndView.addObject("error", error);
			modelAndView.addObject("sidePanelBottom", sidePanelBottom);
			modelAndView.setViewName("admin");
			break;
		}

		modelAndView.addObject("entity", entity);
		modelAndView.addObject("action", action);

		modelAndView.setViewName("admin");
		return modelAndView;
	}

	@PostMapping
	public ModelAndView processUser(Principal principal, ModelAndView modelAndView,
			@ModelAttribute AdminUserForm adminUserForm, @ModelAttribute @Valid User user, BindingResult bindingResult,
			HttpServletRequest request, @RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "selectedUsers", required = false) List<Long> selectedUsersIds,
			@RequestParam(name = "entity", required = false) String entity,
			@RequestParam(name = "action", required = false) String action) {

		if (action.contains(",")) {
			String[] actions = action.split(",");
			action = actions[0];
		}
		LOGGER.debug("[POST] ACTION: {} FOR ENTITY: {} WITH ID(s): {}", action, entity,
				(id == null) || id.isEmpty() ? adminUserForm.getUsersList().toArray() : id);
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
		sidePanelBottom.setTitle("page.header.admin.entity.users");
		sidePanelBottom.setContent(SidePanel.Panel.ADMIN2);
		sidePanelBottom.setVisible(true);
		modelAndView.addObject("sidePanelBottom", sidePanelBottom);
		AdminAction adminAction = AdminAction.fromParam(action);
		switch (adminAction) {
		case CREATE:
			modelAndView = createUser(principal, modelAndView, user, bindingResult);
			break;
		case EDIT:
			modelAndView = editUser(principal, modelAndView, user, bindingResult);
			break;
		case REMOVE:
			modelAndView = removeUser(modelAndView, id, bindingResult);
			// request.setAttribute("action", modelAndView.getModelMap().get("action"));
			// request.setAttribute("allUsers", modelAndView.getModelMap().get("allUsers"));
			// request.setAttribute("removedUsers",
			// modelAndView.getModelMap().get("removedUsers"));
			break;
		case REMOVE_SELECTED:
			adminUserForm.setSelectedUsers(selectedUsersIds);
			modelAndView = removeSelectedUsers(modelAndView, adminUserForm, bindingResult);
			break;

		default:
			bindingResult.reject("page.action.undefined");
			modelAndView.setViewName("admin");
			break;
		}
		return modelAndView;
	}

	// GET

	private ModelAndView listAllUsers(ModelAndView modelAndView) {
		LOGGER.info("[GET] listAllUsers, view: {}", modelAndView.getViewName());
		List<User> allUsers = userService.getAllUsers();
		AdminUserForm form = new AdminUserForm();
		form.setUsersList(allUsers);
		form.setSelectedUsers(new ArrayList<>());
		modelAndView.addObject("allUsers", allUsers);
		// modelAndView.addObject("selectedUsers", new ArrayList<>());
		modelAndView.addObject("adminUserForm", form);
		modelAndView.addObject("removedUsers", modelAndView.getModel().get("removedUsers"));
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "list");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	private ModelAndView prepareEditUserPage(ModelAndView modelAndView, String id) {
		LOGGER.info("[GET] prepareEditUserPage, view: {}", modelAndView.getViewName());
		if ((id != null) && !id.isEmpty()) {
			User user = userService.findById(Long.parseLong(id));
			modelAndView.addObject("user", user);
			modelAndView.addObject("id", id);
			LOGGER.warn("ID: {}", id);
		}

		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "edit");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	private ModelAndView prepareUserDetailsPage(ModelAndView modelAndView, String id) {
		LOGGER.info("[GET] prepareUserDetailsPage, view: {}", modelAndView.getViewName());
		if ((id == null) || id.isEmpty()) {
			FieldError error = new FieldError("user", "id", "user.id.does.not.exists");
			modelAndView.addObject("error", error);
			modelAndView.addObject("entity", "users");
			modelAndView.addObject("action", "details");
			modelAndView.setViewName("admin");
			return modelAndView;
		}
		User user = userService.findById(Long.parseLong(id));
		modelAndView.addObject("user", user);
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "details");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	private ModelAndView prepareCreateUserPage(ModelAndView modelAndView) {
		LOGGER.info("[GET] prepareCreateUserPage, view: {}", modelAndView.getViewName());
		modelAndView.addObject("user", new User());
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "create");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	// POST

	private ModelAndView createUser(Principal principal, ModelAndView modelAndView, User user,
			BindingResult bindingResult) {
		LOGGER.info("[POST] createUser, view: {}", modelAndView.getViewName());
		LOGGER.debug("INCOMMING USER: {}", user);

		if (userService.isUserExists(user.getUsername())) {
			bindingResult.reject("user.exists.already", "user.exists.already");
			modelAndView.getModelMap().addAttribute("user", user);
			modelAndView.addObject("entity", "users");
			modelAndView.addObject("action", "create");
			modelAndView.setViewName("admin");
			return modelAndView;
		}
		user.setCreatedBy(principal.getName());
		User savedUser = userService.saveUser(user);
		// modelAndView.getModelMap().addAttribute("user", savedUser);
		// modelAndView.setViewName("user_details");
		modelAndView.addObject("user", savedUser);
		modelAndView.addObject("id", savedUser.getId());
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "details");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	private ModelAndView editUser(Principal principal, ModelAndView modelAndView, User user,
			BindingResult bindingResult) {
		LOGGER.info("[POST] editUser, view: {}", modelAndView.getViewName());
		LOGGER.debug("EDIT USER: {}", user);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin");
			LOGGER.error("ERRORS! {}", bindingResult.getErrorCount());
			for (ObjectError error : bindingResult.getAllErrors()) {
				LOGGER.error("ERROR ==> {}", bindingResult.getFieldError());
			}
			return modelAndView;
		}
		User userToUpdate = userService.findById(user.getId());
		userToUpdate.setAccountExpired(user.isAccountExpired());
		userToUpdate.setAccountLocked(user.isAccountLocked());
		userToUpdate.setCredentialsExpired(user.isCredentialsExpired());
		userToUpdate.setEnabled(user.isEnabled());
		userToUpdate.setExpires(user.getExpires());
		userToUpdate.setLastModifiedBy(principal.getName());
		userToUpdate.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setUsername(user.getUsername());

		LOGGER.debug("cretedBy: {}", userToUpdate.getCreatedBy());
		LOGGER.warn("SAVING EDITED USER...");
		User savedUser = userService.saveUser(userToUpdate);
		LOGGER.warn("SAVED USER: {}", savedUser);
		modelAndView.addObject("user", savedUser);
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "details");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

	private ModelAndView removeUser(ModelAndView modelAndView, String id) {
		LOGGER.info("[POST] removeUser, view: {}", modelAndView.getViewName());
		User user = userService.findById(Long.parseLong(id));
		if ((id == null) || id.isEmpty() || (user == null)) {
			FieldError error = new FieldError("user", "id", "user.id.does.not.exists");
			modelAndView.addObject("error", error);
			modelAndView.setViewName("admin");
			LOGGER.error("ID");
			return modelAndView;
		}
		userService.deleteUser(user);
		List<User> removedUsers = new ArrayList<>();
		removedUsers.add(user);
		modelAndView.addObject("allUsers", userService.getAllUsers());
		modelAndView.addObject("removedUsers", removedUsers);
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "list");
		modelAndView.getModelMap().addAttribute("entity", "users");
		modelAndView.getModelMap().addAttribute("action", "list");
		modelAndView.setViewName("redirect:/admin/users");
		// modelAndView.setViewName();
		return modelAndView;
	}

	private ModelAndView removeUser(ModelAndView modelAndView, String id, BindingResult bindingResult) {
		LOGGER.info("[POST] removeUser (with bindingResults), view: {}", modelAndView.getViewName());
		if ((id == null) || id.isEmpty()) {

			bindingResult.rejectValue("id", "user.id.does.not.exists");
			modelAndView.setViewName("admin");
			LOGGER.error("ID");
			return modelAndView;
		}
		User user = userService.findById(Long.parseLong(id));
		userService.deleteUser(user);
		List<User> removedUsers = new ArrayList<>();
		List<User> allUsers = userService.getAllUsers();
		removedUsers.add(user);
		modelAndView.addObject("allUsers", allUsers);
		modelAndView.addObject("removedUsers", removedUsers);
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "list");
		modelAndView.getModelMap().addAttribute("entity", "users");
		modelAndView.getModelMap().addAttribute("action", "list");
		modelAndView.getModelMap().addAttribute("allUsers", allUsers);
		modelAndView.getModelMap().addAttribute("removedUsers", removedUsers);
		modelAndView.setViewName("redirect:/admin/users?entity=users&action=list");
		// modelAndView.setViewName();
		return modelAndView;
	}

	private ModelAndView removeSelectedUsers(ModelAndView modelAndView, AdminUserForm form,
			BindingResult bindingResult) {
		LOGGER.info("[POST] removeSelectedUsers, view: {}", modelAndView.getViewName());
		if ((form.getSelectedUsers() == null) || form.getSelectedUsers().isEmpty()
				|| (form.getSelectedUsers().size() == 0)) {
			bindingResult.reject("selected.list.empty");
			LOGGER.error("LIST EMPTY!");
			return modelAndView;
		}
		List<User> removedUsers = new ArrayList<>();
		for (Long userId : form.getSelectedUsers()) {
			LOGGER.debug("USER-ID ====> {}", userId);
			if ((userId != null) && (userId != null)) {
				User userToRemove = userService.findById(userId);
				if (userToRemove == null) {
					bindingResult.rejectValue("id", "user.id.does.not.exists");
					modelAndView.addObject("entity", "users");
					modelAndView.addObject("action", "list");
					modelAndView.setViewName("admin");
					return modelAndView;
				}
				userService.deleteUser(userToRemove);
				removedUsers.add(userToRemove);
			}
		}
		modelAndView.addObject("removedUsers", removedUsers);
		modelAndView.addObject("allUsers", userService.getAllUsers());
		modelAndView.addObject("entity", "users");
		modelAndView.addObject("action", "list");
		modelAndView.setViewName("admin");
		return modelAndView;
	}

}
