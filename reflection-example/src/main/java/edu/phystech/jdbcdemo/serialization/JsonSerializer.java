package edu.phystech.jdbcdemo.serialization;

import org.json.JSONObject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;


public class JsonSerializer<T> {
    private final Set<Field> publishedFields;

    public JsonSerializer(Class<T> serializedClass) {
        publishedFields = ReflectionUtils.getAllFields(serializedClass,
                f -> f.isAnnotationPresent(Published.class));
        publishedFields.forEach(f -> f.setAccessible(true));
    }


    public JSONObject serialize(T o) {
        JSONObject result = new JSONObject();
        for (Field f : publishedFields) {
            Object fieldValue = null;
            try {
                fieldValue = f.get(o);
            } catch (IllegalAccessException e) {
                //this will not happen
                throw new IllegalStateException(e);
            }
            if (fieldValue instanceof LocalDate) {
                result.put(f.getName(), ((LocalDate) fieldValue).format(DateTimeFormatter.ISO_LOCAL_DATE));
            } else {
                result.put(f.getName(), fieldValue);
            }
        }
        return result;
    }
}
