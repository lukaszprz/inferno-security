/**
 * Project: inferno-security-rest-proxy
 * File: InfernoRestExceptionHandler.java
 * Package: pl.inferno.security.proxy.errors.handlers
 * Location:
 * 4 mar 2018 01:50:37 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.errors.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.inferno.security.proxy.errors.InfernoApiError;

/**
 * Class InfernoRestExceptionHandler
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class InfernoRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfernoRestExceptionHandler.class);

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
     * invalid as well.
     *
     * @param ex
     *            HttpMediaTypeNotSupportedException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     *         (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.
     *      ResponseEntityExceptionHandler#handleHttpMediaTypeNotSupported(org.
     *      springframework.web.HttpMediaTypeNotSupportedException,
     *      org.springframework.http.HttpHeaders,
     *      org.springframework.http.HttpStatus,
     *      org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new InfernoApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required'
     * request parameter is missing.
     *
     * @param ex
     *            MissingServletRequestParameterException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     *         (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.
     *      ResponseEntityExceptionHandler#handleMissingServletRequestParameter(org.
     *      springframework.web.bind.MissingServletRequestParameterException,
     *      org.springframework.http.HttpHeaders,
     *      org.springframework.http.HttpStatus,
     *      org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new InfernoApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is
     * malformed.
     *
     * @param ex
     *            HttpMessageNotReadableException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     *         (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.
     *      ResponseEntityExceptionHandler#handleHttpMessageNotReadable(org.
     *      springframework.http.converter.HttpMessageNotReadableException,
     *      org.springframework.http.HttpHeaders,
     *      org.springframework.http.HttpStatus,
     *      org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        LOGGER.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        return buildResponseEntity(new InfernoApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex
     *            HttpMessageNotWritableException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     *         (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.
     *      ResponseEntityExceptionHandler#handleHttpMessageNotWritable(org.
     *      springframework.http.converter.HttpMessageNotWritableException,
     *      org.springframework.http.HttpHeaders,
     *      org.springframework.http.HttpStatus,
     *      org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new InfernoApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.
     * springframework.web.bind.MethodArgumentNotValidException,
     * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        InfernoApiError apiError = new InfernoApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex
     *            the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        InfernoApiError apiError = new InfernoApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(
                String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(InfernoApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
