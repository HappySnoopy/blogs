package net.loyintean.serialnum.web;

import net.loyintean.serialnum.utils.SerialNumUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

/**
 * 使用Apache Http Client时，借助这个拦截器来传递流水号ß
 *
 * @author Snoopy
 * @date 2020年11月4日
 */
public class SerialNumApacheRequestInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest request, HttpContext context) {
        request.addHeader(SerialNumUtils.SERIAL_NUM_KEY, SerialNumUtils.getSerialNum());
    }
}
