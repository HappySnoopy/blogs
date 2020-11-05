package net.loyintean.serialnum.web;

import net.loyintean.serialnum.utils.SerialNumUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 使用OKHttp客户端时，增加一个拦截器
 *
 * @author Snoopy
 * @date 2020年11月4日
 */
public class SerialNumOkHttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request newRequest;
        try {
            // 在原有request基础上，添加一个header
            newRequest = chain.request().newBuilder().addHeader(SerialNumUtils.SERIAL_NUM_KEY, SerialNumUtils.getSerialNum()).build();
        } catch (Exception e) {
            // 万一发生异常，保证原有请求还能正常发送
            newRequest = chain.request();
        }
        return chain.proceed(newRequest);
    }
}
