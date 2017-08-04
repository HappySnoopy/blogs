/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.dynamicenum;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.reflect.ConstructorAccessor;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

/**
 * @author linjun
 * @since 2017年7月10日
 */
public class DynamicEnum {

    private ReflectionFactory reflectionFactory = ReflectionFactory
        .getReflectionFactory();

    /**
     * @author linjun
     * @since 2017年7月10日
     * @param args
     */
    public static void main(String[] args) {

        DynamicEnum dynamicEnum = new DynamicEnum();
        // Dynamically add 3 new enum instances d, e, f to TestEnum
        dynamicEnum.addEnum(TestEnum.class, "d");
        dynamicEnum.addEnum(TestEnum.class, "e");
        dynamicEnum.addEnum(TestEnum.class, "f");

        // Run a few tests just to show it works OK.
        System.out.println(Arrays.deepToString(TestEnum.values()));
        // Shows : [a, b, c, d, e, f]

        TestEnum testEnum = TestEnum.valueOf("e");

        switch (testEnum) {
            case A:
                System.out.println("A");
                break;

            default:
                System.out.println(testEnum);
                break;
        }

    }

    /**
     * Add an enum instance to the enum class given as argument
     *
     * @param enumType
     * @param enumName
     */
    public <T extends Enum<?>> void addEnum(Class<T> enumType,
            String enumName) {

        // 0. Sanity checks
        if (!Enum.class.isAssignableFrom(enumType)) {
            throw new RuntimeException(
                "class " + enumType + " is not an instance of Enum");
        }
        // 1. Lookup "$VALUES" holder in enum class and get previous enum instances
        Field valuesField = null;
        Field[] fields = TestEnum.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().contains("$VALUES")) {
                valuesField = field;
                break;
            }
        }
        AccessibleObject.setAccessible(new Field[] { valuesField }, true);

        try {
            if (valuesField != null) {

                // 2. Copy it
                T[] previousValues = (T[]) valuesField.get(enumType);
                List<Object> values = new ArrayList<>(
                    Arrays.asList(previousValues));

                // 3. build new enum
                T newValue = (T) this.makeEnum(enumType, // The target enum class
                    enumName, // THE NEW ENUM INSTANCE TO BE DYNAMICALLY ADDED
                    values.size(), new Class[] {}, // can be used to pass values to the enum constuctor
                    new Object[] {}); // can be used to pass values to the enum constuctor

                // 4. add new value
                values.add(newValue);

                // 5. Set new values field
                this.setFailsafeFieldValue(valuesField, null,
                    values.toArray((T[]) Array.newInstance(enumType, 0)));

                // 6. Clean enum cache
                this.cleanEnumCache(enumType);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Object makeEnum(Class<?> enumClass, String value, int ordinal,
            Class<?>[] additionalTypes, Object[] additionalValues)
            throws Exception {
        Object[] parms = new Object[additionalValues.length + 2];
        parms[0] = value;
        parms[1] = Integer.valueOf(ordinal);
        System.arraycopy(additionalValues, 0, parms, 2,
            additionalValues.length);
        return enumClass
            .cast(this.getConstructorAccessor(enumClass, additionalTypes)
                .newInstance(parms));
    }

    private ConstructorAccessor getConstructorAccessor(Class<?> enumClass,
            Class<?>[] additionalParameterTypes) throws NoSuchMethodException {
        Class<?>[] parameterTypes = new Class[additionalParameterTypes.length
            + 2];
        parameterTypes[0] = String.class;
        parameterTypes[1] = int.class;
        System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2,
            additionalParameterTypes.length);
        return this.reflectionFactory.newConstructorAccessor(
            enumClass.getDeclaredConstructor(parameterTypes));
    }

    private void setFailsafeFieldValue(Field field, Object target, Object value)
            throws NoSuchFieldException, IllegalAccessException {

        // let's make the field accessible
        field.setAccessible(true);

        // next we change the modifier in the Field instance to
        // not be final anymore, thus tricking reflection into
        // letting us modify the static final field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        int modifiers = modifiersField.getInt(field);

        // blank out the final bit in the modifiers int
        modifiers &= ~Modifier.FINAL;
        modifiersField.setInt(field, modifiers);
        System.err.println("========= " + target + " ================= " + field
            + " =============== " + value);
        // 设值

        /*
         * try {
         * BeanUtils.setProperty(target, field.getName(), value);
         * } catch (InvocationTargetException e) {
         * e.printStackTrace();
         * }
         */

        FieldAccessor fa = this.reflectionFactory.newFieldAccessor(field,
            false);
        fa.set(target, value);

    }

    private void cleanEnumCache(Class<?> enumClass)
            throws NoSuchFieldException, IllegalAccessException {
        //        this.blankField(enumClass, "enumConstantDirectory"); // Sun (Oracle?!?) JDK 1.5/6

        this.blankField(enumClass, "enumConstants"); // IBM JDK
    }

    private void blankField(Class<?> enumClass, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        for (Field field : Class.class.getDeclaredFields()) {
            if (field.getName().contains(fieldName)) {
                AccessibleObject.setAccessible(new Field[] { field }, true);
                this.setFailsafeFieldValue(field, enumClass, null);
                break;
            }
        }
    }
}
