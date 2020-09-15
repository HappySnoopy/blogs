package net.loyintean.test;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 构建测试数据。
 * <p>
 * 随机处理数据
 *
 * @author 林俊
 * @date 2019 -07-18
 */
@SuppressWarnings("unchecked")
public class DataBuilder<T> {

    private static final Logger log = LoggerFactory.getLogger(DataBuilder.class);

    /**
     * 要构建的数据实例
     */
    private T target;

    /**
     * @param targetClz the target clz
     */
    private DataBuilder(Class<T> targetClz) {
        try {
            this.target = targetClz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("初始化实例失败。targetClz:{}", targetClz, e);
        }
    }

    /**
     * Of data builder.
     *
     * @param <T>       the type parameter
     * @param targetClz the target clz
     * @return the data builder
     */
    public static <T> DataBuilder<T> of(Class<T> targetClz) {
        return new DataBuilder<>(targetClz);
    }


    private static final Map<Predicate<Field>, Function<Field, Object>> GLOBAL = new LinkedHashMap<>();

    static {
        GLOBAL.put(field -> Integer.class.equals(field.getType()), field -> RandomUtils.nextInt());
        GLOBAL.put(field -> Long.class.equals(field.getType()), field -> RandomUtils.nextLong());
        GLOBAL.put(field -> String.class.equals(field.getType()), field -> RandomStringUtils.randomAlphabetic(16));
        GLOBAL.put(field -> Boolean.class.equals(field.getType()), field -> RandomUtils.nextBoolean());
        GLOBAL.put(field -> field.getType().isEnum(), field -> {
            // 如果是枚举，随机选一个
            @SuppressWarnings("rawtypes")
            List<Enum> enumList = EnumUtils.getEnumList((Class<Enum>) field.getType());
            return enumList.get(RandomUtils.nextInt(0, enumList.size()));
        });

        // 加一个垫底的。只要有默认构造方法，就用DataBuilder递归式地构造构造一个出来
        GLOBAL.put(field -> field.getType().getConstructors()[0].getParameterCount() == 0,
                field -> DataBuilder.of(field.getDeclaringClass()).build());

    }

    private Map<Predicate<Field>, Function<Field, Object>> byName = new LinkedHashMap<>();
    private Map<Predicate<Field>, Function<Field, Object>> byType = new LinkedHashMap<>();

    /**
     * Do build.
     *
     * @param field the field
     */
    private void doBuild(Field field) {

        Object value = Stream.of(byName, byType, GLOBAL)
                .flatMap(m -> m.entrySet().stream())
                .filter(e -> e.getKey().test(field))
                .map(e -> e.getValue().apply(field))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        try {
            if (value != null) {
                field.setAccessible(true);
                field.set(target, value);
            }
        } catch (IllegalAccessException e) {
            log.error("设值出错。target:{}, value:{}", target, value, e);
        }
    }

    /**
     * 利用反射，向Object中的字段填充随机值
     */
    public T build() {

        // 一直遍历到父类
        Field[] fields;
        for (Class<?> clz = target.getClass(); clz != null; clz = clz.getSuperclass()) {
            fields = clz.getDeclaredFields();

            // 遍历其中所有字段
            for (Field field : fields) {
                doBuild(field);
            }
        }
        return target;
    }

    public DataBuilder<T> registry(String fieldName, Function<Field, Object> mapper) {
        byName.put(f -> f.getName().equals(fieldName), mapper);
        return this;
    }

    public DataBuilder<T> registry(Class<?> fieldType, Function<Field, Object> mapper) {
        byName.put(f -> f.getType().equals(fieldType), mapper);
        return this;
    }

    public DataBuilder<T> registry(String fieldName, Object value) {
        byName.put(f -> f.getName().equals(fieldName), f -> value);
        return this;
    }
}