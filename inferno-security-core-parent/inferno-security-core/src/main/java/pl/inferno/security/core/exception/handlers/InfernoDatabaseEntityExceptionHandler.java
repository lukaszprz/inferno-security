/**
 * Project: inferno-security-core
 * File: InfernoDatabaseEntityExceptionHandler.java
 * Package: pl.inferno.security.core.exception.handlers
 * Location:
 * 4 mar 2018 02:49:24 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.core.exception.handlers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.inferno.security.core.exception.InfernoApiError;

/**
 * Class InfernoDatabaseEntityExceptionHandler
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class InfernoDatabaseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
     * fails.
     *
     * @param ex
     *            the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        InfernoApiError apiError = new InfernoApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB
     * causes.
     *
     * @param ex
     *            the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new InfernoApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
        }
        return buildResponseEntity(new InfernoApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    /**
     * Handle javax.persistence.EntityNotFoundException
     */
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return buildResponseEntity(new InfernoApiError(HttpStatus.NOT_FOUND, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(InfernoApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
