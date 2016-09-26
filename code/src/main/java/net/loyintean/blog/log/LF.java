/**
 * All Rights Reserved
 */
package net.loyintean.blog.log;

import org.apache.commons.lang3.StringUtils;

/**
 * 日志格式化工具
 * <p>
 * 为了简化代码中的使用，使用简写类名“LF”。全名是LogFormatter
 *
 * @author winters1224@163.com
 */
public class LF {

    /**
     * 默认格式化类
     * <p>
     * 默认是 {@link LogFormatHelperAsKeyValue}
     */
    private static LogFormatterHelper defaultFormatter = new LogFormatHelperAsKeyValue();

    /**
     * “绳索”数据，用于串联起线程内的日志
     * <p>
     * NOPMD 不知道为什么PMD扫描后认为它是UnusedPrivateField，但这个字段实际被使用了。
     */
    private static final ThreadLocalCord CORD = new ThreadLocalCord(); // NOPMD

    /**
     * 把names转换为格式化的日志占位符串。
     * <p>
     * 占位符是：{}<br>
     * 如果当前线程已经定义过“绳索”信息了，那么在当前names前面，加上这段数据。<br>
     * 为了简化代码使用，使用简写名“ns”。全名是“names”
     *
     * @param names
     * @return
     */
    public static String ns(String... names) {

        // 由于有initValue，这里确保会返回一个实例。
        Cord cord = LF.CORD.get();
        if (cord.inited()) {
            // 如果已经初始化过了，那么把cord的日志放到第一位
            // 把names的格式化日志跟在后面
            return LF.defaultFormatter.format(cord.getKey(), cord.getValue())
                + LF.defaultFormatter.names(names);
        } else {
            // 如果还没有初始化，那么直接返回names的格式化字符串，而不做CORD的初始化操作。
            return LF.defaultFormatter.names(names);
        }
    }

    /**
     * 尝试将name用作绳索；并输出一个格式化字符串
     * <p>
     * 如果直接委托给 {@link #cn(String...)}，每次都需要new一个数组。不太划算。
     *
     * @param name
     * @return 格式化的一个字符串。<br>
     *         如果同一线程内重复调用此方法，则返回第一次调用时生成的数据。
     */
    public static String cn(String name) {
        // 由于有initValue，这里确保会返回一个实例。
        Cord cord = LF.CORD.get();

        if (cord.inited()) {
            // 如果已经初始化过了，那么把cord的日志放到第一位
            // 把names的格式化日志跟在后面
            return LF.defaultFormatter.format(cord.getKey(), cord.getValue())
                + LF.defaultFormatter.names(name);
        } else {
            // 如果还没有初始化，那么把names[0]放入cord中，
            cord.setKey(name);
            return LF.defaultFormatter.names(name);
        }
    }

    /**
     * names中的第一个值将作为“绳索”使用。<br>
     * names数据仍由 {@link #defaultFormatter}来负责格式化。
     *
     * @param names
     * @return 格式化的一个字符串。<br>
     *         如果同一线程内重复调用此方法，则返回第一次调用时生成的数据。
     */
    public static String cn(String... names) {
        // 由于有initValue，这里确保会返回一个实例。
        Cord cord = LF.CORD.get();

        if (cord.inited()) {
            // 如果已经初始化过了，那么把cord的日志放到第一位
            // 把names的格式化日志跟在后面
            return LF.defaultFormatter.format(cord.getKey(), cord.getValue())
                + LF.defaultFormatter.names(names);
        } else {
            // 如果还没有初始化，那么把names[0]放入cord中，
            cord.setKey(names[0]);
            return LF.defaultFormatter.names(names);
        }
    }

    /**
     * 定义一个“绳索”，用于把同一线程内、不同位置输出的日志串起来。
     * <p>
     * 如果同一线程内重复调用此方法，则：保留第一份数据，丢弃后来的。<br>
     * 使用多线程的情况下可能需要重新声明。<br>
     * 为了简化代码使用，使用简写名“cv”。全名是“cordValue”
     *
     * @param value
     * @return 格式化的一个字符串。<br>
     *         如果同一线程内重复调用此方法，则返回第一次调用时生成的数据。
     */
    public static String cv(String value) {
        // 由于有initValue，这里确保会返回一个实例。
        Cord cord = LF.CORD.get();
        if (!cord.inited()) {
            cord.setValue(value);
        }
        return value;
    }

    /**
     * 移除当前的绳索
     * <p>
     * 在使用线程池时，可能出现一个线程执行多个业务逻辑。<br>
     * 此时，需要在业务逻辑执行完毕后，移除当前线程上下文。<br>
     * 否则，绳索将失去意义。
     */
    public static void remove() {
        LF.CORD.remove();
    }

    /**
     * “绳索”类
     *
     * @author winters1224@163.com
     */
    private static class Cord {
        private String key;
        private String value;

        /**
         * @return the {@link #key}
         */
        public String getKey() {
            return this.key;
        }

        /**
         * @param key
         *        the {@link #key} to set
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * @return the {@link #value}
         */
        public String getValue() {
            return this.value;
        }

        /**
         * @param value
         *        the {@link #value} to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * {@link #key}/{@link #value} 都非空时，才返回true
         *
         * @return
         */
        public boolean inited() {
            return StringUtils.isNoneBlank(this.key, this.value);
        }
    }

    /**
     * “绳索”的线程容器
     *
     * @author winters1224@163.com
     */
    private static class ThreadLocalCord extends ThreadLocal<Cord> {
        /**
         * @see java.lang.ThreadLocal#initialValue()
         */
        @Override
        protected Cord initialValue() {
            return new Cord();
        }

    }

}
