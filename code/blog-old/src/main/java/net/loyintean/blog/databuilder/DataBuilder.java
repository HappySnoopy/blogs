package net.loyintean.blog.databuilder;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 构建测试数据。
 * <p>
 * 随机处理数据
 *
 * @author 林俊
 * @date 2019 -07-18
 */
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

    /**
     * Do build.
     *
     * @param field the field
     */
    private void doBuild(Field field) {

        Object value;
        if (Integer.class.equals(field.getType())) {
            value = RandomUtils.nextInt();
        } else if (Long.class.equals(field.getType())) {
            value = RandomUtils.nextLong();
        } else if (String.class.equals(field.getType())) {
            value = RandomStringUtils.randomAlphabetic(16);
        } else if (Boolean.class.equals(field.getType())) {
            value = RandomUtils.nextBoolean();
        } else if (field.getType().isEnum()) {
            // 如果是枚举
            List<Enum> enumList = EnumUtils.getEnumList((Class<Enum>) field.getType());
            value = enumList.get(RandomUtils.nextInt(0, enumList.size()));
        } else {
            //            其它类型，暂不处理
            value = null;
        }

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
        for (Class clz = target.getClass(); clz != null; clz = clz.getSuperclass()) {
            fields = clz.getDeclaredFields();

            // 遍历其中所有字段
            for (Field field : fields) {
                doBuild(field);
            }
        }
        return target;
    }
}