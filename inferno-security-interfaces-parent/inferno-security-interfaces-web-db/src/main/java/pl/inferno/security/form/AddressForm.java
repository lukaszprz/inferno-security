/**
 * 
 */
package pl.inferno.security.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.inferno.security.core.model.Address;
import pl.inferno.validation.annotation.PostCode;

/**
 * Class AddressForm
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class AddressForm implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8319660642048149382L;

	/**
	 * Logger LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressForm.class);

	/**
	 * String ADDRESS_FORM_OBJECT_NAME
	 */
	public static final String ADDRESS_FORM_OBJECT_NAME = "addressForm";

	/**
	 * Class AddressType
	 *
	 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
	 *
	 */
	public static class AddressType {
		/**
		 * Class Type
		 *
		 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
		 *
		 */
		public enum Type {
			/**
			 * Type MAIN
			 */
			MAIN("main"),
			/**
			 * Type CORRESPONDENCE
			 */
			CORRESPONDENCE("correspondence"),
			/**
			 * Type ADDITIONAL
			 */
			ADDITIONAL("additional");

			/**
			 * String param
			 */
			private String param;

			/**
			 * @param param
			 */
			private Type(String param) {
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

	/**
	 * Class FormActions
	 *
	 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
	 *
	 */
	public static class FormActions {
		/**
		 * Class Action
		 *
		 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
		 *
		 */
		public enum Action {
			// @formatter:off
			/**
			 * Action EDIT
			 */
			EDIT("edit"),
			/**
			 * Action DELETE
			 */
			DELETE("delete"),
			/**
			 * Action ADD
			 */
			ADD("add");
			// @formatter:on

			/**
			 * String param
			 */
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

	/**
	 * Long id
	 */
	private Long id;

	/**
	 * String action
	 */
	private String action;

	/**
	 * String type
	 */
	private String type;

	/**
	 * String street
	 */
	private String street;

	/**
	 * String buildingNumber
	 */
	private String buildingNumber;

	/**
	 * String appartment
	 */
	private String appartment;

	/**
	 * String postCode
	 */
	@PostCode
	private String postCode;

	/**
	 * String city
	 */
	private String city;

	/**
	 * String district
	 */
	private String district;

	/**
	 * boolean mainAddressDefined
	 */
	private boolean mainAddressDefined = false;

	/**
	 * boolean correspondenceAddressDefined
	 */
	private boolean correspondenceAddressDefined = false;

	/**
	 * boolean additionalAddressDefined
	 */
	private boolean additionalAddressDefined = false;

	/**
	 * int countAddresses
	 */
	private int countAddresses;

	/**
	 * boolean allowAdd
	 */
	private boolean allowAdd;

	/**
	 * List<InfernoErrorObject> errors
	 */
	private List<InfernoErrorObject> errors;

	/**
	 * SuccessfullAction successfullAction
	 */
	private SuccessfullAction successfullAction;

	/**
	 * List<Address> usersDefinedAddresses
	 */
	private List<Address> usersDefinedAddresses = new ArrayList<>();

	/**
	 * Map<AddressType.Type,Address> usersAddressesMap
	 */
	private Map<AddressType.Type, Address> usersAddressesMap = new HashMap<>();

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the buildingNumber
	 */
	public String getBuildingNumber() {
		return buildingNumber;
	}

	/**
	 * @param buildingNumber
	 *            the buildingNumber to set
	 */
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	/**
	 * @return the appartment
	 */
	public String getAppartment() {
		return appartment;
	}

	/**
	 * @param appartment
	 *            the appartment to set
	 */
	public void setAppartment(String appartment) {
		this.appartment = appartment;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the mainAddressDefined
	 */
	public boolean isMainAddressDefined() {
		return mainAddressDefined;
	}

	/**
	 * @param mainAddressDefined
	 *            the mainAddressDefined to set
	 */
	public void setMainAddressDefined(boolean mainAddressDefined) {
		this.mainAddressDefined = mainAddressDefined;
	}

	/**
	 * @return the countAddresses
	 */
	public int getCountAddresses() {
		return countAddresses;
	}

	/**
	 * @param countAddresses
	 *            the countAddresses to set
	 */
	public void setCountAddresses(int countAddresses) {
		this.countAddresses = countAddresses;
	}

	/**
	 * @return the allowAdd
	 */
	public boolean isAllowAdd() {
		return allowAdd;
	}

	/**
	 * @param allowAdd
	 *            the allowAdd to set
	 */
	public void setAllowAdd(boolean allowAdd) {
		this.allowAdd = allowAdd;
	}

	/**
	 * @return the correspondenceAddressDefined
	 */
	public boolean isCorrespondenceAddressDefined() {
		return correspondenceAddressDefined;
	}

	/**
	 * @param correspondenceAddressDefined
	 *            the correspondenceAddressDefined to set
	 */
	public void setCorrespondenceAddressDefined(boolean correspondenceAddressDefined) {
		this.correspondenceAddressDefined = correspondenceAddressDefined;
	}

	/**
	 * @return the additionalAddressDefined
	 */
	public boolean isAdditionalAddressDefined() {
		return additionalAddressDefined;
	}

	/**
	 * @param additionalAddressDefined
	 *            the additionalAddressDefined to set
	 */
	public void setAdditionalAddressDefined(boolean additionalAddressDefined) {
		this.additionalAddressDefined = additionalAddressDefined;
	}

	/**
	 * @return the usersDefinedAddresses
	 */
	public List<Address> getUsersDefinedAddresses() {
		return usersDefinedAddresses;
	}

	/**
	 * @param usersDefinedAddresses
	 *            the usersDefinedAddresses to set
	 */
	public void setUsersDefinedAddresses(List<Address> usersDefinedAddresses) {
		this.usersDefinedAddresses = usersDefinedAddresses;
	}

	/**
	 * @return the usersAddressesMap
	 */
	public Map<AddressType.Type, Address> getUsersAddressesMap() {
		return usersAddressesMap;
	}

	/**
	 * @param usersAddressesMap
	 *            the usersAddressesMap to set
	 */
	public void setUsersAddressesMap(Map<AddressType.Type, Address> usersAddressesMap) {
		this.usersAddressesMap = usersAddressesMap;
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
		result = (prime * result) + (additionalAddressDefined ? 1231 : 1237);
		result = (prime * result) + (allowAdd ? 1231 : 1237);
		result = (prime * result) + ((appartment == null) ? 0 : appartment.hashCode());
		result = (prime * result) + ((buildingNumber == null) ? 0 : buildingNumber.hashCode());
		result = (prime * result) + ((city == null) ? 0 : city.hashCode());
		result = (prime * result) + (correspondenceAddressDefined ? 1231 : 1237);
		result = (prime * result) + countAddresses;
		result = (prime * result) + ((district == null) ? 0 : district.hashCode());
		result = (prime * result) + ((errors == null) ? 0 : errors.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result) + (mainAddressDefined ? 1231 : 1237);
		result = (prime * result) + ((postCode == null) ? 0 : postCode.hashCode());
		result = (prime * result) + ((street == null) ? 0 : street.hashCode());
		result = (prime * result) + ((successfullAction == null) ? 0 : successfullAction.hashCode());
		result = (prime * result) + ((type == null) ? 0 : type.hashCode());
		result = (prime * result) + ((usersAddressesMap == null) ? 0 : usersAddressesMap.hashCode());
		result = (prime * result) + ((usersDefinedAddresses == null) ? 0 : usersDefinedAddresses.hashCode());
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
		if (!(obj instanceof AddressForm)) {
			return false;
		}
		AddressForm other = (AddressForm) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (additionalAddressDefined != other.additionalAddressDefined) {
			return false;
		}
		if (allowAdd != other.allowAdd) {
			return false;
		}
		if (appartment == null) {
			if (other.appartment != null) {
				return false;
			}
		} else if (!appartment.equals(other.appartment)) {
			return false;
		}
		if (buildingNumber == null) {
			if (other.buildingNumber != null) {
				return false;
			}
		} else if (!buildingNumber.equals(other.buildingNumber)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (correspondenceAddressDefined != other.correspondenceAddressDefined) {
			return false;
		}
		if (countAddresses != other.countAddresses) {
			return false;
		}
		if (district == null) {
			if (other.district != null) {
				return false;
			}
		} else if (!district.equals(other.district)) {
			return false;
		}
		if (errors == null) {
			if (other.errors != null) {
				return false;
			}
		} else if (!errors.equals(other.errors)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (mainAddressDefined != other.mainAddressDefined) {
			return false;
		}
		if (postCode == null) {
			if (other.postCode != null) {
				return false;
			}
		} else if (!postCode.equals(other.postCode)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (successfullAction == null) {
			if (other.successfullAction != null) {
				return false;
			}
		} else if (!successfullAction.equals(other.successfullAction)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (usersAddressesMap == null) {
			if (other.usersAddressesMap != null) {
				return false;
			}
		} else if (!usersAddressesMap.equals(other.usersAddressesMap)) {
			return false;
		}
		if (usersDefinedAddresses == null) {
			if (other.usersDefinedAddresses != null) {
				return false;
			}
		} else if (!usersDefinedAddresses.equals(other.usersDefinedAddresses)) {
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
		builder.append("AddressForm [");
		if (id != null) {
			builder.append("id=").append(id).append(", ");
		}
		if (action != null) {
			builder.append("action=").append(action).append(", ");
		}
		if (type != null) {
			builder.append("type=").append(type).append(", ");
		}
		if (street != null) {
			builder.append("street=").append(street).append(", ");
		}
		if (buildingNumber != null) {
			builder.append("buildingNumber=").append(buildingNumber).append(", ");
		}
		if (appartment != null) {
			builder.append("appartment=").append(appartment).append(", ");
		}
		if (postCode != null) {
			builder.append("postCode=").append(postCode).append(", ");
		}
		if (city != null) {
			builder.append("city=").append(city).append(", ");
		}
		if (district != null) {
			builder.append("district=").append(district).append(", ");
		}
		builder.append("mainAddressDefined=").append(mainAddressDefined).append(", correspondenceAddressDefined=")
				.append(correspondenceAddressDefined).append(", additionalAddressDefined=")
				.append(additionalAddressDefined).append(", countAddresses=").append(countAddresses)
				.append(", allowAdd=").append(allowAdd).append(", ");
		if (errors != null) {
			builder.append("errors=").append(errors).append(", ");
		}
		if (successfullAction != null) {
			builder.append("successfullAction=").append(successfullAction).append(", ");
		}
		if (usersDefinedAddresses != null) {
			builder.append("usersDefinedAddresses=").append(usersDefinedAddresses).append(", ");
		}
		if (usersAddressesMap != null) {
			builder.append("usersAddressesMap=").append(usersAddressesMap);
		}
		builder.append("]");
		return builder.toString();
	}

}
