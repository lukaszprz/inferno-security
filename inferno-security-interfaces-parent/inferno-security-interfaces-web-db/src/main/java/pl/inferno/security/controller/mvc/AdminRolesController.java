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

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.service.RoleService;
import pl.inferno.security.form.AdminAction;
import pl.inferno.security.form.AdminRoleForm;
import pl.inferno.security.form.SidePanel;

/**
 * Class AdminRolesController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Controller
@RequestMapping("/admin/roles")
public class AdminRolesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminRolesController.class);

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ModelAndView init(HttpServletRequest request) {
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
		sidePanelBottom.setTitle("page.header.admin.entity.roles");
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
			modelAndView = listAllRoles(modelAndView);
			break;
		case CREATE:
			modelAndView = prepareCreateRolePage(modelAndView);
			break;
		case EDIT:
			modelAndView = prepareEditRolePage(modelAndView, id);
			break;
		case REMOVE_SINGLE:
			modelAndView = removeRole(modelAndView, id);
			break;
		case DETAILS:
			modelAndView = prepareRoleDetailsPage(modelAndView, id);
			modelAndView.addObject("sidePanelBottom", sidePanelBottom);
			break;

		default:
			ObjectError error = new ObjectError("role", "page.action.undefined");
			modelAndView.addObject("error", error);
			modelAndView.addObject("sidePanelBottom", sidePanelBottom);
			modelAndView.setViewName("role");
			break;
		}

		modelAndView.addObject("entity", entity);
		modelAndView.addObject("action", action);

		modelAndView.setViewName("role");
		return modelAndView;
	}

	@PostMapping
	public ModelAndView processUser(Principal principal, ModelAndView modelAndView,
			@ModelAttribute AdminRoleForm adminRoleForm, @ModelAttribute @Valid Role role, BindingResult bindingResult,
			HttpServletRequest request, @RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "selectedRoles", required = false) List<Long> selectedRolesIds,
			@RequestParam(name = "entity", required = false) String entity,
			@RequestParam(name = "action", required = false) String action) {

		LOGGER.debug("[POST] ACTION: {} FOR ENTITY: {} WITH ID(s): {}", action, entity,
				(id == null) || id.isEmpty() ? adminRoleForm.getRolesList().toArray() : id);
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
		sidePanelBottom.setTitle("page.header.admin.entity.roles");
		sidePanelBottom.setContent(SidePanel.Panel.ADMIN2);
		sidePanelBottom.setVisible(true);
		modelAndView.addObject("sidePanelBottom", sidePanelBottom);
		AdminAction adminAction = AdminAction.fromParam(action);
		switch (adminAction) {
		case CREATE:
			modelAndView = createRole(principal, modelAndView, role, bindingResult);
			break;
		case EDIT:
			modelAndView = editRole(principal, modelAndView, role, bindingResult);
			break;
		case REMOVE:
			modelAndView = removeRole(modelAndView, id, bindingResult);
			break;
		case REMOVE_SELECTED:
			adminRoleForm.setSelectedRoles(selectedRolesIds);
			modelAndView = removeSelectedRoles(modelAndView, adminRoleForm, bindingResult);
			break;

		default:
			bindingResult.reject("page.action.undefined");
			modelAndView.setViewName("role");
			break;
		}
		return modelAndView;
	}

	// GET

	private ModelAndView listAllRoles(ModelAndView modelAndView) {
		List<Role> allRoles = roleService.findAllRoles();
		AdminRoleForm form = new AdminRoleForm();
		form.setRolesList(allRoles);
		modelAndView.addObject("allRoles", allRoles);
		modelAndView.addObject("adminRoleForm", form);
		modelAndView.addObject("removedRoles", modelAndView.getModel().get("removedRoles"));
		modelAndView.setViewName("role");
		return modelAndView;
	}

	private ModelAndView prepareCreateRolePage(ModelAndView modelAndView) {
		modelAndView.addObject("role", new Role());
		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "create");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	private ModelAndView prepareEditRolePage(ModelAndView modelAndView, String id) {
		if ((id != null) && !id.isEmpty()) {
			Role role = roleService.findRoleById(Long.parseLong(id));

			modelAndView.addObject("role", role);
			modelAndView.addObject("id", id);
			LOGGER.warn("ID: {}", id);
		}

		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "edit");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	private ModelAndView removeRole(ModelAndView modelAndView, String id) {

		Role role = roleService.findRoleById(Long.parseLong(id));
		if ((id == null) || id.isEmpty() || (role == null)) {
			FieldError error = new FieldError("user", "id", "role.id.does.not.exists");
			modelAndView.addObject("error", error);
			modelAndView.setViewName("role");
			LOGGER.error("ID");
			return modelAndView;
		}
		roleService.deleteRole(role);
		List<Role> removedRoles = new ArrayList<>();
		removedRoles.add(role);
		modelAndView.addObject("allRoles", roleService.findAllRoles());
		modelAndView.addObject("removedRoles", removedRoles);
		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "list");
		modelAndView.getModelMap().addAttribute("entity", "roles");
		modelAndView.getModelMap().addAttribute("action", "list");
		modelAndView.setViewName("redirect:/role");
		// modelAndView.setViewName();
		return modelAndView;
	}

	private ModelAndView prepareRoleDetailsPage(ModelAndView modelAndView, String id) {

		if ((id == null) || id.isEmpty()) {
			FieldError error = new FieldError("role", "id", "role.id.does.not.exists");
			modelAndView.addObject("error", error);
			modelAndView.addObject("entity", "roles");
			modelAndView.addObject("action", "details");
			modelAndView.setViewName("role");
			return modelAndView;
		}
		Role role = roleService.findRoleById(Long.parseLong(id));
		modelAndView.addObject("role", role);
		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "details");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	// POST

	private ModelAndView createRole(Principal principal, ModelAndView modelAndView, Role role,
			BindingResult bindingResult) {
		LOGGER.debug("INCOMMING ROLE: {}", role);

		if (roleService.isRoleExists(role.getName())) {
			bindingResult.reject("role.exists.already", "role.exists.already");
			modelAndView.getModelMap().addAttribute("role", role);
			modelAndView.addObject("entity", "roles");
			modelAndView.addObject("action", "create");
			modelAndView.setViewName("role");
			return modelAndView;
		}
		role.setCreatedBy(principal.getName());
		boolean savedRole = roleService.saveRole(role);
		modelAndView.addObject("role", role);
		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "details");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	private ModelAndView editRole(Principal principal, ModelAndView modelAndView, Role role,
			BindingResult bindingResult) {
		LOGGER.debug("EDIT ROLE: {}", role);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("role");
			LOGGER.error("ERRORS! {}", bindingResult.getErrorCount());
			for (ObjectError error : bindingResult.getAllErrors()) {
				LOGGER.error("ERROR ==> {}", bindingResult.getFieldError());
			}
			return modelAndView;
		}
		boolean modified = false;
		Role roleToUpdate = roleService.findRoleById(role.getId());
		if ((role.getName() != null) && !role.getName().isEmpty() && !role.getName().equals(roleToUpdate.getName())) {
			roleToUpdate.setName(role.getName());
			modified = true;
		}
		if ((role.getDescription() != null) && !role.getDescription().isEmpty()
				&& !role.getDescription().equals(roleToUpdate.getDescription())) {
			roleToUpdate.setDescription(role.getDescription());
			modified = true;
		}
		if ((role.getValidTo() != null) && !role.getValidTo().equals(roleToUpdate.getValidTo())) {
			roleToUpdate.setValidTo(role.getValidTo());
			modified = true;
		}
		if (modified) {
			roleToUpdate.setLastModifiedBy(principal.getName());
			roleToUpdate.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		}

		roleService.saveRole(roleToUpdate);

		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "details");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param id
	 * @param bindingResult
	 * @return
	 */
	private ModelAndView removeRole(ModelAndView modelAndView, String id, BindingResult bindingResult) {
		List<Role> removedRoles = new ArrayList<>();
		List<Role> allRoles = roleService.findAllRoles();
		Role roleToRemove = roleService.findRoleById(Long.valueOf(id));
		if (roleToRemove != null) {
			removedRoles.add(roleToRemove);
			// roleService.deleteRole(roleToRemove);
		}

		modelAndView.addObject("allRoles", allRoles);
		modelAndView.addObject("removedRoles", removedRoles);
		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "list");
		modelAndView.getModelMap().addAttribute("entity", "roles");
		modelAndView.getModelMap().addAttribute("action", "list");
		modelAndView.getModelMap().addAttribute("allRoles", allRoles);
		modelAndView.getModelMap().addAttribute("removedRoles", removedRoles);
		modelAndView.setViewName("role");
		return modelAndView;
	}

	private ModelAndView removeSelectedRoles(ModelAndView modelAndView, AdminRoleForm form,
			BindingResult bindingResult) {

		if ((form.getSelectedRoles() == null) || form.getSelectedRoles().isEmpty()
				|| (form.getSelectedRoles().size() == 0)) {
			bindingResult.reject("selected.list.empty");
			LOGGER.error("LIST EMPTY!");
			return modelAndView;
		}
		List<Role> removedRoles = new ArrayList<>();
		for (Long roleId : form.getSelectedRoles()) {
			LOGGER.debug("ROLE-ID ====> {}", roleId);
			if ((roleId != null) && (roleId != null)) {
				Role roleToRemove = roleService.findRoleById(roleId);

				if (roleToRemove == null) {
					bindingResult.rejectValue("id", "role.id.does.not.exists");
					modelAndView.addObject("entity", "roles");
					modelAndView.addObject("action", "list");
					modelAndView.setViewName("role");
					return modelAndView;
				}
				roleService.deleteRole(roleToRemove);
				removedRoles.add(roleToRemove);
			}
		}
		modelAndView.addObject("removedRoles", removedRoles);
		modelAndView.addObject("allRoles", roleService.findAllRoles());
		modelAndView.addObject("entity", "roles");
		modelAndView.addObject("action", "list");
		modelAndView.setViewName("role");
		return modelAndView;
	}
}
