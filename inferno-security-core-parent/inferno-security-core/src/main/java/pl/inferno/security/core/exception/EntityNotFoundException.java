/**
 * Project: inferno-security-rest-proxy
 * File: EntityNotFoundException.java
 * Package: pl.inferno.security.proxy.errors
 * Location:
 * 4 mar 2018 00:53:17 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.core.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

/**
 * Class EntityNotFoundException
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class EntityNotFoundException extends Exception {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -1770236796901580342L;

    public EntityNotFoundException(Class<?> clazz, String... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " was not found for parameters " + searchParams;
    }

    private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if ((entries.length % 2) == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new, (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])), Map::putAll);
    }
}
