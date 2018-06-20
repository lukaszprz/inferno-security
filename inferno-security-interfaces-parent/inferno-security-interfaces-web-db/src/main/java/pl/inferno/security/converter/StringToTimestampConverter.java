/**
 *
 */
package pl.inferno.security.converter;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Class StringToTimestampConverter
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Component
public class StringToTimestampConverter implements Converter<String, Timestamp> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringToTimestampConverter.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public Timestamp convert(String source) {
		if ((source == null) || source.isEmpty()) {
			return null;
		}
		return Timestamp.valueOf(source);
	}
}
