/**
 * Project: inferno-security-interfaces-web-db
 * File: InfernoGlobalDefaultExceptionHandler.java
 * Package: pl.inferno.security.errors.handler
 * Location:
 * 14 mar 2018 08:11:36 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.errors.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class InfernoGlobalDefaultExceptionHandler
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@ControllerAdvice
public class InfernoGlobalDefaultExceptionHandler implements WebArgumentResolver {

	public static final String DEFAULT_ERROR_VIEW = "error";

	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.addObject("url", request.getRequestURL());
		modelAndView.addObject("localizedMessage", e.getLocalizedMessage());
		modelAndView.addObject("message", e.getMessage());

		modelAndView.setViewName(DEFAULT_ERROR_VIEW);
		return modelAndView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.
	 * springframework.core.MethodParameter,
	 * org.springframework.web.context.request.NativeWebRequest)
	 */
	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
		methodParameter.getParameterName();
		// String eTag = response.// application-specific calculation
		// long lastModified = // application-specific calculation
		// if (request.checkNotModified(eTag, lastModified)) {
		// // shortcut exit - no further processing necessary
		// return null;
		// }
		// // further request processing, actually building content
		// model.addAttribute(...);
		// return "myViewName";
		return null;
	}

}
