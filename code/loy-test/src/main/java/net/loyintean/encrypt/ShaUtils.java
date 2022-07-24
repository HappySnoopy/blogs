package net.loyintean.encrypt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Secure Hash Algorithm
 * 安全散列算法。计算得到长度固定的字符串（即信息摘要）。
 * 据称比MD5更安全
 */
public class ShaUtils {

    private static final String KEY_SHA = "SHA";
    private static final String ALGORITHM = "SHA-256";

    public static String encrypt(String data) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(Base64Utils.decode(data));
        return Base64Utils.encode(sha.digest());
    }

    public static String encrypt256(String data) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha = MessageDigest.getInstance(ALGORITHM);
        sha.update(Base64Utils.decode(data));
        return Base64Utils.encode(sha.digest());
    }
}
