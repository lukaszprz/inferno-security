/**
 *
 */
package pl.inferno.security.form;

/**
 * Class AdminAction
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public enum AdminAction {

	// @formatter:off
	LIST("list"),
	CREATE("create"),
	REMOVE("remove"),
	REMOVE_SELECTED("remove-selected"),
	REMOVE_SINGLE("remove-single"),
	DETAILS("details"),
	EDIT("edit"),
	MAIN("main");
	// @formatter:on

	private String param;

	/**
	 * @param param
	 */
	private AdminAction(String param) {
		this.param = param;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	// From String method will return you the Enum for the provided input string
	public static AdminAction fromParam(String param) {
		if (param != null) {
			for (AdminAction adminAction : AdminAction.values()) {
				if (param.equalsIgnoreCase(adminAction.param)) {
					return adminAction;
				}
			}
		}
		return null;
	}

}
