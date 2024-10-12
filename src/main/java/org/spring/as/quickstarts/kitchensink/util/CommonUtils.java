package org.spring.as.quickstarts.kitchensink.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CommonUtils {
    private CommonUtils() {
    }

    public static <T> T objectToPojoConverter(Object object, Class<T> dto) {
        try {
            return objectMapper().convertValue(object, dto);
        } catch (IllegalArgumentException e) {
            log.error("Error while creating response from object.", e);
        }
        return null;
    }

    public static <S, T> List<T> objectToPojoConverter(List<S> source, Class<T> targetClass) {
        try {
            return source
                    .stream()
                    .map(element -> objectMapper().convertValue(element, targetClass))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            log.error("Error while creating response from object.", e);
        }
        return null;
    }

    private static ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return objectMapper;
    }
}
