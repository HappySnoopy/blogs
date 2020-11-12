package net.loyintean.serialnum.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * 流水号工具类。负责管理流水号的统一操作
 *
 * @author Snoopy
 * @date 2020年11月4日
 */
public class SerialNumUtils {

    /**
     * 流水号在MDC和在http-header、message-properties中的key值
     */
    public static final String SERIAL_NUM_KEY = "serialNum";

    /**
     * 生成随机流水号
     *
     * @return 返回8位随机数
     */
    private static String randomSerialNum() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    /**
     * 初始化一个流水号
     *
     * @return 返回初始化后的流水号
     */
    public static String initSerialNum() {
        MDC.put(SERIAL_NUM_KEY, randomSerialNum());
        return MDC.get(SERIAL_NUM_KEY);
    }

    /**
     * 获取当前流水号。如果当前没有流水号，则生成一个随机流水号（并写入MDC中）
     *
     * @return 当前流水号
     */
    public static String getSerialNum() {
        return StringUtils.defaultIfBlank(MDC.get(SERIAL_NUM_KEY), initSerialNum());
    }

    /**
     * 设置随机流水号。如果入参是null，将随机生成一个。
     *
     * @param serialNum 指定的流水号
     */
    public static void setSerialNum(Object serialNum) {
        MDC.put(SERIAL_NUM_KEY, ObjectUtils.defaultIfNull(serialNum, randomSerialNum()).toString());
    }

    /**
     * 清除当前流水号
     */
    public static void clearSerialNum() {
        MDC.remove(SERIAL_NUM_KEY);
    }

    /**
     * 在当前流水号的基础上追加一个流水号。
     * <p>
     * 一般是在一个请求触发多个请求的时候、或者是所谓“跳跃”的地方使用ß
     *
     * @param serialNum the serial num
     */
    public static void appendSerialNum(Object serialNum) {
        setSerialNum(getSerialNum() + "-" + ObjectUtils.defaultIfNull(serialNum, randomSerialNum()).toString());
    }
}
