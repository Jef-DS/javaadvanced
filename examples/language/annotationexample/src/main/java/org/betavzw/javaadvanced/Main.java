package org.betavzw.javaadvanced;





import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by Jef on 1/09/2016.
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TestClass tc = new TestClass();
        saveCall(tc, 5, -5);
    }
    public static void saveCall(Object o, Integer ... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = o.getClass();
        Method m = c.getMethod("method", Integer.class, Integer.class);
        for (int i=0; i< m.getParameterCount(); i++){
            if (m.getParameters()[i].getAnnotation(NonNegative.class) != null) {
                if (args[i] < 0) throw new IllegalArgumentException("Parameter cannot be negative");
            }
        }
        m.invoke(o, args);
    }
}
