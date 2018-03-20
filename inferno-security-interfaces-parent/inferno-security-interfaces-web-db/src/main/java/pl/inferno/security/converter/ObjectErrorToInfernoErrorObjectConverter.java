/**
 * 
 */
package pl.inferno.security.converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import pl.inferno.security.errors.handler.Severity;
import pl.inferno.security.form.InfernoErrorObject;
import pl.inferno.security.utils.MessageUtils;

/**
 * Class ObjectErrorToInfernoErrorObjectConverter
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Component
public class ObjectErrorToInfernoErrorObjectConverter implements Converter<ObjectError, InfernoErrorObject> {

	private final static Logger LOGGER = LoggerFactory.getLogger(ObjectErrorToInfernoErrorObjectConverter.class);

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public InfernoErrorObject convert(ObjectError source) {

		LOGGER.debug("CONVERTER OF ERROR-TYPE: {}", source.getClass().getTypeName());
		LOGGER.debug("OBJECT ERROR TO BE CONVERTED: {}", source.toString());
		InfernoErrorObject infernoErrorObject = null;
		if (source instanceof FieldError) {
			FieldError fieldError = (FieldError) source;
			infernoErrorObject = new InfernoErrorObject(fieldError.getObjectName(), null, null, null);
			infernoErrorObject.setObjectName(fieldError.getObjectName());
			infernoErrorObject.setFieldName(fieldError.getField());
			infernoErrorObject.setGlobal(false);
			infernoErrorObject.setArguments(fieldError.getArguments());
			infernoErrorObject.setCode(fieldError.getCode());
			infernoErrorObject.setCodes(fieldError.getCodes());
			infernoErrorObject.setRejectedValue(fieldError.getRejectedValue());
			LOGGER.debug("ARGUMENTS: {} [{}]", fieldError.getArguments(), fieldError.getArguments().length);
			boolean overridenArgs = false;
			List<Object> argumentsList = Arrays.asList(fieldError.getArguments());
			for (Object obj : argumentsList) {
				LOGGER.debug("ARGUMENT TYPE: {}", obj.getClass().getName());
				if (obj instanceof DefaultMessageSourceResolvable) {
					overridenArgs = true;
					DefaultMessageSourceResolvable defaultError = (DefaultMessageSourceResolvable) obj;

					LOGGER.debug("{} ==> {} ==> {}", defaultError.getClass().getName(), defaultError.getCodes(),
							fieldError.getDefaultMessage());
					Map<String, Object> argsMap = new HashMap<>();
					argsMap.put(MessageUtils.SEVERITY_MAP_KEY, MessageUtils.renderSeverityMessage(Severity.ERROR));
					argsMap.put(MessageUtils.VALUE_MAP_KEY, defaultError.getCode());
					LOGGER.debug("VALUE: {}", argsMap.get(MessageUtils.VALUE_MAP_KEY));
					String severity = messageSource.getMessage((String) argsMap.get(MessageUtils.SEVERITY_MAP_KEY),
							null, LocaleContextHolder.getLocale());
					infernoErrorObject.setCode(fieldError.getDefaultMessage());

					infernoErrorObject.setSeverity(severity);
					infernoErrorObject.setValue((String) argsMap.get(MessageUtils.VALUE_MAP_KEY));
					infernoErrorObject.setArguments(new Object[] { argsMap });
				}
			}
			if (fieldError.getArguments().length > 0 && !overridenArgs) {
				Map<String, Object> argumentsMap = (Map<String, Object>) fieldError.getArguments()[0];
				String severity = messageSource.getMessage((String) argumentsMap.get(MessageUtils.SEVERITY_MAP_KEY),
						null, LocaleContextHolder.getLocale());
				LOGGER.debug("SEVERITY: {}", severity);
				infernoErrorObject.setSeverity(severity);
				infernoErrorObject.setValue((String) argumentsMap.get(MessageUtils.VALUE_MAP_KEY));
				infernoErrorObject.setArgumentsMap(argumentsMap);
			}

		} else if (source instanceof ObjectError) {
			ObjectError objectError = (ObjectError) source;
			infernoErrorObject = new InfernoErrorObject(objectError.getObjectName(), null);
			infernoErrorObject.setObjectName(objectError.getObjectName());
			infernoErrorObject.setGlobal(true);
			infernoErrorObject.setCode(objectError.getCode());
			infernoErrorObject.setCodes(objectError.getCodes());
			infernoErrorObject.setArguments(objectError.getArguments());
			LOGGER.debug("ARGUMENTS: {} [{}]", objectError.getArguments(), objectError.getArguments().length);
			if (objectError.getArguments().length > 0) {
				Map<String, Object> argumentsMap = (Map<String, Object>) objectError.getArguments()[0];
				for (String key : argumentsMap.keySet()) {
					LOGGER.debug("{} =====> {}", key, argumentsMap.get(key));
				}
				String severity = messageSource.getMessage((String) argumentsMap.get(MessageUtils.SEVERITY_MAP_KEY),
						null, LocaleContextHolder.getLocale());
				LOGGER.debug("SEVERITY: {}", severity);
				infernoErrorObject.setSeverity(severity);
				infernoErrorObject.setValue((String) argumentsMap.get(MessageUtils.VALUE_MAP_KEY));
				infernoErrorObject.setArgumentsMap(argumentsMap);
			}

		}
		return infernoErrorObject;
	}

}
