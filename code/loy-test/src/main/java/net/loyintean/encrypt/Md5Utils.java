package net.loyintean.encrypt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密
 */
public class Md5Utils {

    public static final String KEY_MD5 = "MD5";

    public static byte[] encrypt(byte[] data) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);

        md5.update(data);

        return md5.digest();
    }

    public static String encrypt(String data) throws NoSuchAlgorithmException, IOException {

        byte[] result = encrypt(Base64Utils.decode(data));

        return Base64Utils.encode(result);
    }

}
