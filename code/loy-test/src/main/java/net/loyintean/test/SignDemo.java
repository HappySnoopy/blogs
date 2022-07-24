package net.loyintean.test;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: chen
 * \* Date: 2018/9/21
 * \* Time: 17:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SignDemo {
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String getSignMd5(LinkedHashMap<String, String> map) throws UnsupportedEncodingException {
        String url = getURL(map);
        return MD5(url);
    }

    private static String getURL(LinkedHashMap<String, String> map) throws UnsupportedEncodingException {
        String md5 = "";
        Set<String> keys = map.keySet();
        Iterator var3 = keys.iterator();

        while (var3.hasNext()) {
            String key = (String) var3.next();
            String value = map.get(key);
            if (key != null) {
                md5 = md5 + key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
            } else {
                md5 = md5 + key + "=&";
            }
        }

        md5 = md5.substring(0, md5.length() - 1);
        return md5;
    }

    private static String MD5(String origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            origin = byteArrayToHexString(md.digest(origin.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.getMessage();
        }

        return origin;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for (int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = b + 256;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) throws Exception {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("submitTimestamp", "20160204192820");// 请求时间戳
        map.put("pid", "1234");
        map.put("cardBatchNo", "1234");
        map.put("password", "1234");
        map.put("orderId", "20180921170830312977591");
        map.put("customerNo", "1234567890");
        map.put("orderQuantity", String.valueOf(5));// 换购数量
        map.put("orderTime", "2012121212");// 换购时间,YYYYMMDDHH24MISS
        map.put("orderPoints", String.valueOf(100));// 换购积分数
        map.put("orderPrice", String.valueOf(100));//换购总金额
//        map.put("provUser", "33");//用户来自哪个省
//        map.put("provUse", String.valueOf(33));//使用目的地省份
        String sign = SignDemo.getSignMd5(map);
        System.out.println("签名=" + sign);
    }
}