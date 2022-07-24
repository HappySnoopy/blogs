package net.loyintean.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64Utils {

    /**
     * 基于Base64编码，接收byte[]并转换成String
     */
    private static final BASE64Encoder ENCODER = new BASE64Encoder();
    private static final BASE64Decoder DECODER = new BASE64Decoder();

    public static String encode(String data) {
        return encode(data.getBytes());
    }

    public static String encode(byte[] bytes) {
        return ENCODER.encode(bytes);
    }


    public static byte[] decode(String data) throws IOException {
        return DECODER.decodeBuffer(data);
    }

    public static String decode2Str(String data) throws IOException {
        return new String(decode(data));
    }

}
