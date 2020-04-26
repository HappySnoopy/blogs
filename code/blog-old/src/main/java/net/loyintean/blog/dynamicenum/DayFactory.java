package net.loyintean.blog.dynamicenum;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DayFactory {

    public static void say() {
        try {
            Constructor con = TestEnum.class.getDeclaredConstructors()[0];
            Method[] methods = con.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equals("acquireConstructorAccessor")) {
                    m.setAccessible(true);
                    m.invoke(con);
                }
            }
            Field[] fields = con.getClass().getDeclaredFields();
            Object ca = null;
            for (Field f : fields) {
                if (f.getName().equals("constructorAccessor")) {
                    f.setAccessible(true);
                    ca = f.get(con);
                }
            }
            if (ca != null) {

                Method m = ca.getClass().getMethod("newInstance", Object[].class);
                m.setAccessible(true);
                TestEnum v = (TestEnum) m.invoke(ca, new Object[] {
                    new Object[] { "VACATION",
                        TestEnum.values().length + 1 } });
                System.out
                    .println(v.getClass() + ":" + v.name() + ":" + v.ordinal());

                for (TestEnum day : TestEnum.values()) {
                    System.out.println(day);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void ddd() {
        DayFactory.say();
    }
}