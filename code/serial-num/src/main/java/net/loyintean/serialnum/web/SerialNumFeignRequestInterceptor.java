package net.loyintean.serialnum.web;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import net.loyintean.serialnum.utils.SerialNumUtils;

/**
 * 使用Feign时，通过HttpRequest Header 传递流水号
 *
 * @author Snoopy
 * @date 2020年11月4日
 */
public class SerialNumFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(SerialNumUtils.SERIAL_NUM_KEY, SerialNumUtils.getSerialNum());
    }
}
