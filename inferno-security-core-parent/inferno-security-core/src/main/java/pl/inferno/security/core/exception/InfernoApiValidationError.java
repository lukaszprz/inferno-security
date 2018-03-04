/**
 * Project: inferno-security-rest-proxy
 * File: InfernoApiValidationError.java
 * Package: pl.inferno.security.proxy.errors
 * Location:
 * 4 mar 2018 01:12:40 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.core.exception;

/**
 * Class InfernoApiValidationError
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class InfernoApiValidationError extends InfernoAbstractApiSubError {

    private String object;

    private String field;

    private Object rejectedValue;

    private String message;

    /**
     * @param object
     * @param message
     */
    public InfernoApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    /**
     *
     */
    public InfernoApiValidationError() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param object
     * @param field
     * @param rejectedValue
     * @param message
     */
    public InfernoApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    /**
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * @param object
     *            the object to set
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(String field) {
        this.field = field;
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((field == null) ? 0 : field.hashCode());
        result = (prime * result) + ((message == null) ? 0 : message.hashCode());
        result = (prime * result) + ((object == null) ? 0 : object.hashCode());
        result = (prime * result) + ((rejectedValue == null) ? 0 : rejectedValue.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
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
        if (!(obj instanceof InfernoApiValidationError)) {
            return false;
        }
        InfernoApiValidationError other = (InfernoApiValidationError) obj;
        if (field == null) {
            if (other.field != null) {
                return false;
            }
        } else if (!field.equals(other.field)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (object == null) {
            if (other.object != null) {
                return false;
            }
        } else if (!object.equals(other.object)) {
            return false;
        }
        if (rejectedValue == null) {
            if (other.rejectedValue != null) {
                return false;
            }
        } else if (!rejectedValue.equals(other.rejectedValue)) {
            return false;
        }
        return true;
    }

}
