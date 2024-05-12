package helpers;

import jdk.jfr.Description;

import java.lang.reflect.Field;

public class EnumHelper {

    public static <T> String getDescription(T source) {
        try {
            Field field = source.getClass().getField(source.toString());
            Description[] annotations = field.getAnnotationsByType(Description.class);
            if (annotations != null && annotations.length > 0) {
                return annotations[0].value();
            }
        } catch (NoSuchFieldException e) {
            // Field not found, fall back to default behavior
        }
        return source.toString();
    }
}

