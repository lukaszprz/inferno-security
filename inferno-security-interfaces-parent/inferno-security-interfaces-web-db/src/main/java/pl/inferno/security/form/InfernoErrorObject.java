/**
 * 
 */
package pl.inferno.security.form;

import java.util.Arrays;
import java.util.Map;

import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.ObjectError;

import pl.inferno.security.errors.handler.Severity;

/**
 * Class InfernoErrorObject
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class InfernoErrorObject extends ObjectError {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -8330106109957242464L;

	private String objectName;

	private String fieldName;

	private String code;

	private String[] codes;

	private Object[] arguments;

	private String severity;

	private String value;

	private Object rejectedValue;

	private boolean global;

	private Map<String, Object> argumentsMap;

	/**
	 * @param objectName
	 * @param codes
	 * @param arguments
	 * @param defaultMessage
	 */
	public InfernoErrorObject(String objectName, String[] codes, Object[] arguments, String defaultMessage) {
		super(objectName, codes, arguments, defaultMessage);
		this.objectName = objectName;
		this.codes = codes;
		this.arguments = arguments;
		this.code = defaultMessage;
	}

	/**
	 * @param objectName
	 * @param defaultMessage
	 */
	public InfernoErrorObject(String objectName, String defaultMessage) {
		super(objectName, defaultMessage);
		this.objectName = objectName;
		this.code = defaultMessage;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName
	 *            the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the codes
	 */
	public String[] getCodes() {
		return codes;
	}

	/**
	 * @param codes
	 *            the codes to set
	 */
	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	/**
	 * @return the arguments
	 */
	public Object[] getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the rejectedValue
	 */
	public Object getRejectedValue() {
		return rejectedValue;
	}

	/**
	 * @param rejectedValue
	 *            the rejectedValue to set
	 */
	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	/**
	 * @return the global
	 */
	public boolean isGlobal() {
		return global;
	}

	/**
	 * @param global
	 *            the global to set
	 */
	public void setGlobal(boolean global) {
		this.global = global;
	}

	/**
	 * @return the argumentsMap
	 */
	public Map<String, Object> getArgumentsMap() {
		return argumentsMap;
	}

	/**
	 * @param argumentsMap
	 *            the argumentsMap to set
	 */
	public void setArgumentsMap(Map<String, Object> argumentsMap) {
		this.argumentsMap = argumentsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(arguments);
		result = prime * result + ((argumentsMap == null) ? 0 : argumentsMap.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + Arrays.hashCode(codes);
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + (global ? 1231 : 1237);
		result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
		result = prime * result + ((rejectedValue == null) ? 0 : rejectedValue.hashCode());
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof InfernoErrorObject)) {
			return false;
		}
		InfernoErrorObject other = (InfernoErrorObject) obj;
		if (!Arrays.equals(arguments, other.arguments)) {
			return false;
		}
		if (argumentsMap == null) {
			if (other.argumentsMap != null) {
				return false;
			}
		} else if (!argumentsMap.equals(other.argumentsMap)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (!Arrays.equals(codes, other.codes)) {
			return false;
		}
		if (fieldName == null) {
			if (other.fieldName != null) {
				return false;
			}
		} else if (!fieldName.equals(other.fieldName)) {
			return false;
		}
		if (global != other.global) {
			return false;
		}
		if (objectName == null) {
			if (other.objectName != null) {
				return false;
			}
		} else if (!objectName.equals(other.objectName)) {
			return false;
		}
		if (rejectedValue == null) {
			if (other.rejectedValue != null) {
				return false;
			}
		} else if (!rejectedValue.equals(other.rejectedValue)) {
			return false;
		}
		if (severity == null) {
			if (other.severity != null) {
				return false;
			}
		} else if (!severity.equals(other.severity)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
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
		builder.append("InfernoErrorObject [");
		if (objectName != null) {
			builder.append("objectName=").append(objectName).append(", ");
		}
		if (fieldName != null) {
			builder.append("fieldName=").append(fieldName).append(", ");
		}
		if (code != null) {
			builder.append("code=").append(code).append(", ");
		}
		if (codes != null) {
			builder.append("codes=").append(Arrays.toString(codes)).append(", ");
		}
		if (arguments != null) {
			builder.append("arguments=").append(Arrays.toString(arguments)).append(", ");
		}
		if (severity != null) {
			builder.append("severity=").append(severity).append(", ");
		}
		if (value != null) {
			builder.append("value=").append(value).append(", ");
		}
		if (rejectedValue != null) {
			builder.append("rejectedValue=").append(rejectedValue).append(", ");
		}
		builder.append("global=").append(global).append(", ");
		if (argumentsMap != null) {
			builder.append("argumentsMap=").append(argumentsMap);
		}
		builder.append("]");
		return builder.toString();
	}

}
