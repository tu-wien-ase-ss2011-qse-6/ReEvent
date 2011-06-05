package reevent.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SuperTypeTokenUtil {
    @SuppressWarnings("unchecked")
    public static Class findToken(Class<?> clazz, int tokenIndex) {
        Type superclass = clazz.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type entityType = ((ParameterizedType) superclass).getActualTypeArguments()[tokenIndex];
        if (!(entityType instanceof Class)) {
            throw new RuntimeException("Entity type not a class.");
        }
        return (Class) entityType;
    }

}
