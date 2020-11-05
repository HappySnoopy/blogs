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

    public static final String SERIAL_NUM_KEY = "serialNum";

    private static String randomSerialNum() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    public static void initSerialNum() {
        MDC.put(SERIAL_NUM_KEY, randomSerialNum());
    }

    public static String getSerialNum() {
        return StringUtils.defaultIfBlank(MDC.get(SERIAL_NUM_KEY), randomSerialNum());
    }

    public static void setSerialNum(Object serialNum) {
        MDC.put(SERIAL_NUM_KEY, ObjectUtils.defaultIfNull(serialNum, randomSerialNum()).toString());
    }

    public static void clearSerialNum() {
        MDC.remove(SERIAL_NUM_KEY);
    }
}
