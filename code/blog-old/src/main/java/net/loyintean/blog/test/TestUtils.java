/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Snoopy
 */
public class TestUtils {

    public static final <T> T invokePrivateMethod(Object instance, String name,
            Object... args) {

        Class<?>[] clazArray = new Class<?>[args.length];

        for (int i = 0; i < args.length; i++) {
            clazArray[i] = args[i].getClass();
        }

        try {
            Method method = instance.getClass().getDeclaredMethod(name,
                clazArray);
            method.setAccessible(true);

            T result = (T) method.invoke(instance, args);
            return result;

        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | InvocationTargetException e) {

            throw new RuntimeException(e);
        }
    }

    public static final <T> T invokePrivateMethod(Object instance, String name) {

        try {
            Method method = instance.getClass().getDeclaredMethod(name);
            method.setAccessible(true);

            T result = (T) method.invoke(instance);
            return result;

        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | InvocationTargetException e) {

            throw new RuntimeException(e);
        }
    }
}
