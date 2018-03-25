/**
 * Project: inferno-security-interfaces-web-db
 * File: UserForm.java
 * Package: pl.inferno.security.form
 * Location:
 * 13 mar 2018 15:53:00 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class UserForm
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
// @Component
public class UserForm implements Serializable {

	/**
	 * String USER_FORM_OBJECT_NAME
	 */
	public static final String USER_FORM_OBJECT_NAME = "userForm";

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -7498783885899200788L;

	public static class FormActions {
		public enum Action {
			CHANGE_PASSWORD("changePassword"), EDIT_USER("editUser");

			private String param;

			/**
			 * @param param
			 */
			private Action(String param) {
				this.param = param;
			}

			/**
			 * @return the param
			 */
			public String getParam() {
				return param;
			}

			/**
			 * @param param
			 *            the param to set
			 */
			public void setParam(String param) {
				this.param = param;
			}
		}
	}

	private String action;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	private String firstName;

	private String lastName;

	private Map<String, Object> messages;

	private String newPassword1;

	private String newPassword2;

	private UserForm oldForm;

	private String oldPassword;

	private String username;

	private String email;

	private String homePhoneNumber;

	private String mobilePhoneNumber;

	/**
	 * List<InfernoErrorObject> errors
	 */
	private List<InfernoErrorObject> errors;

	/**
	 * SuccessfullAction successfullAction
	 */
	private SuccessfullAction successfullAction;

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the messages
	 */
	public Map<String, Object> getMessages() {
		return messages;
	}

	/**
	 * @return the newPassword1
	 */
	public String getNewPassword1() {
		return newPassword1;
	}

	/**
	 * @return the newPassword2
	 */
	public String getNewPassword2() {
		return newPassword2;
	}

	/**
	 * @return the oldForm
	 */
	public UserForm getOldForm() {
		return oldForm;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(Map<String, Object> messages) {
		this.messages = messages;
	}

	/**
	 * @param newPassword1
	 *            the newPassword1 to set
	 */
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	/**
	 * @param newPassword2
	 *            the newPassword2 to set
	 */
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	/**
	 * @param oldForm
	 *            the oldForm to set
	 */
	public void setOldForm(UserForm oldForm) {
		this.oldForm = oldForm;
	}

	/**
	 * @param oldPassword
	 *            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the errors
	 */
	public List<InfernoErrorObject> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<InfernoErrorObject> errors) {
		this.errors = errors;
	}

	/**
	 * @return the successfullAction
	 */
	public SuccessfullAction getSuccessfullAction() {
		return successfullAction;
	}

	/**
	 * @param successfullAction
	 *            the successfullAction to set
	 */
	public void setSuccessfullAction(SuccessfullAction successfullAction) {
		this.successfullAction = successfullAction;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the homePhoneNumber
	 */
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	/**
	 * @param homePhoneNumber
	 *            the homePhoneNumber to set
	 */
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber
	 *            the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((action == null) ? 0 : action.hashCode());
		result = (prime * result) + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		result = (prime * result) + ((errors == null) ? 0 : errors.hashCode());
		result = (prime * result) + ((firstName == null) ? 0 : firstName.hashCode());
		result = (prime * result) + ((homePhoneNumber == null) ? 0 : homePhoneNumber.hashCode());
		result = (prime * result) + ((lastName == null) ? 0 : lastName.hashCode());
		result = (prime * result) + ((messages == null) ? 0 : messages.hashCode());
		result = (prime * result) + ((mobilePhoneNumber == null) ? 0 : mobilePhoneNumber.hashCode());
		result = (prime * result) + ((newPassword1 == null) ? 0 : newPassword1.hashCode());
		result = (prime * result) + ((newPassword2 == null) ? 0 : newPassword2.hashCode());
		result = (prime * result) + ((oldForm == null) ? 0 : oldForm.hashCode());
		result = (prime * result) + ((oldPassword == null) ? 0 : oldPassword.hashCode());
		result = (prime * result) + ((successfullAction == null) ? 0 : successfullAction.hashCode());
		result = (prime * result) + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserForm)) {
			return false;
		}
		UserForm other = (UserForm) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (errors == null) {
			if (other.errors != null) {
				return false;
			}
		} else if (!errors.equals(other.errors)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (homePhoneNumber == null) {
			if (other.homePhoneNumber != null) {
				return false;
			}
		} else if (!homePhoneNumber.equals(other.homePhoneNumber)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (messages == null) {
			if (other.messages != null) {
				return false;
			}
		} else if (!messages.equals(other.messages)) {
			return false;
		}
		if (mobilePhoneNumber == null) {
			if (other.mobilePhoneNumber != null) {
				return false;
			}
		} else if (!mobilePhoneNumber.equals(other.mobilePhoneNumber)) {
			return false;
		}
		if (newPassword1 == null) {
			if (other.newPassword1 != null) {
				return false;
			}
		} else if (!newPassword1.equals(other.newPassword1)) {
			return false;
		}
		if (newPassword2 == null) {
			if (other.newPassword2 != null) {
				return false;
			}
		} else if (!newPassword2.equals(other.newPassword2)) {
			return false;
		}
		if (oldForm == null) {
			if (other.oldForm != null) {
				return false;
			}
		} else if (!oldForm.equals(other.oldForm)) {
			return false;
		}
		if (oldPassword == null) {
			if (other.oldPassword != null) {
				return false;
			}
		} else if (!oldPassword.equals(other.oldPassword)) {
			return false;
		}
		if (successfullAction == null) {
			if (other.successfullAction != null) {
				return false;
			}
		} else if (!successfullAction.equals(other.successfullAction)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserForm [");
		if (action != null) {
			builder.append("action=").append(action).append(", ");
		}
		if (dateOfBirth != null) {
			builder.append("dateOfBirth=").append(dateOfBirth).append(", ");
		}
		if (firstName != null) {
			builder.append("firstName=").append(firstName).append(", ");
		}
		if (lastName != null) {
			builder.append("lastName=").append(lastName).append(", ");
		}
		if (messages != null) {
			builder.append("messages=").append(messages).append(", ");
		}
		if (newPassword1 != null) {
			builder.append("newPassword1=").append(newPassword1).append(", ");
		}
		if (newPassword2 != null) {
			builder.append("newPassword2=").append(newPassword2).append(", ");
		}
		if (oldForm != null) {
			builder.append("oldForm=").append(oldForm).append(", ");
		}
		if (oldPassword != null) {
			builder.append("oldPassword=").append(oldPassword).append(", ");
		}
		if (username != null) {
			builder.append("username=").append(username).append(", ");
		}
		if (email != null) {
			builder.append("email=").append(email).append(", ");
		}
		if (homePhoneNumber != null) {
			builder.append("homePhoneNumber=").append(homePhoneNumber).append(", ");
		}
		if (mobilePhoneNumber != null) {
			builder.append("mobilePhoneNumber=").append(mobilePhoneNumber).append(", ");
		}
		if (errors != null) {
			builder.append("errors=").append(errors).append(", ");
		}
		if (successfullAction != null) {
			builder.append("successfullAction=").append(successfullAction);
		}
		builder.append("]");
		return builder.toString();
	}

}
