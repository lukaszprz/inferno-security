/**
 * 
 */
package pl.inferno.security.core.resolvers;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Class ParseDeserializer
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class ParseDeserializer extends StdDeserializer<LocalDate> {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 2178491462176861749L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ParseDeserializer.class);

    public ParseDeserializer() {
	super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
	    throws IOException, JsonProcessingException {
	return LocalDate.parse(p.getValueAsString()); // or overloaded with an appropriate format
    }
}
