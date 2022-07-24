package net.loyintean.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class HttpClientTest {

    @Test
    public void test() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        // 注意这里是LinkedHashMap，put参数必须保持这个顺序
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        param.put("submitTimestamp", date);
        param.put("pid", "wntest");
        param.put("cardBatchNo", "202007270278");
        param.put("password", "135246");
        param.put("orderId", RandomStringUtils.randomAlphanumeric(10));
        param.put("customerNo", "13488717901");
        param.put("orderQuantity", "1");
        param.put("orderTime", date);
        param.put("orderPoints", "1");
        param.put("orderPrice", "5");
        param.put("provUser", "33");
        param.put("provUse", "33");
        param.put("signMsg", SignDemo.getSignMd5(param));

        String queryString = param.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));

        String url = "http://testwx.jifenfu.net/orderCard2!order.do?" + queryString;

        System.out.println("url = " + url);

        HttpGet request = new HttpGet();

        request.addHeader("content-type", "application/json;charset=utf-8");
        request.setURI(URI.create(url));

        HttpResponse response = httpClient.execute(request);

        // 注意，这里返回的是null。使用feign的话可能需要检查一下是否可以反序列化。
        System.out.println("content-type: " + response.getEntity().getContentType());
        System.out.println("responseBody: " + EntityUtils.toString(response.getEntity()));


    }
}
