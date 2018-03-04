/**
 * Project: inferno-security-rest-proxy
 * File: LowerCaseClassNameResolver.java
 * Package: pl.inferno.security.proxy.resolvers
 * Location:
 * 4 mar 2018 00:59:46 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.core.resolvers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Class LowerCaseClassNameResolver
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class LowerCaseClassNameResolver extends TypeIdResolverBase {

    /**
     *
     */
    public LowerCaseClassNameResolver() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param baseType
     * @param typeFactory
     */
    public LowerCaseClassNameResolver(JavaType baseType, TypeFactory typeFactory) {
        super(baseType, typeFactory);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see
     * com.fasterxml.jackson.databind.jsontype.TypeIdResolver#idFromValue(java.lang.
     * Object)
     */
    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.fasterxml.jackson.databind.jsontype.TypeIdResolver#idFromValueAndType(
     * java.lang.Object, java.lang.Class)
     */
    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    /*
     * (non-Javadoc)
     * @see com.fasterxml.jackson.databind.jsontype.TypeIdResolver#getMechanism()
     */
    @Override
    public Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

}
