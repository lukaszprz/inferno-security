/**
 * 
 */
package pl.inferno.security.core.resolvers;

import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;

/**
 * Class InfernoDateTimeDeserializer
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class InfernoLocalDateTimeDeserializer extends LocalDateDeserializer {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 6754159027938744187L;

    /**
     * @param cls
     * @param format
     */
    public InfernoLocalDateTimeDeserializer() {
	super();

    }

}
