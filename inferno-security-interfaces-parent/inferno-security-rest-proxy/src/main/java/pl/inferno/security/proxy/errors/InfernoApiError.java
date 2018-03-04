/**
 * Project: inferno-security-rest-proxy
 * File: InfernoApiError.java
 * Package: pl.inferno.security.proxy.errors
 * Location:
 * 4 mar 2018 01:22:01 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import pl.inferno.security.proxy.resolvers.LowerCaseClassNameResolver;

/**
 * Class InfernoApiError
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class InfernoApiError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<InfernoAbstractApiSubError> subErrors;

    /**
     *
     */
    private InfernoApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * @param status
     */
    public InfernoApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * @param status
     */
    public InfernoApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * @param status
     * @param message
     */
    public InfernoApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private void addSubError(InfernoAbstractApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new InfernoApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new InfernoApiValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when
     * a @Validated validation fails.
     *
     * @param cv
     *            the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(cv.getRootBeanClass().getSimpleName(), ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    /**
     * @return the debugMessage
     */
    public String getDebugMessage() {
        return debugMessage;
    }

    /**
     * @param debugMessage
     *            the debugMessage to set
     */
    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    /**
     * @return the subErrors
     */
    public List<InfernoAbstractApiSubError> getSubErrors() {
        return subErrors;
    }

    /**
     * @param subErrors
     *            the subErrors to set
     */
    public void setSubErrors(List<InfernoAbstractApiSubError> subErrors) {
        this.subErrors = subErrors;
    }

}
