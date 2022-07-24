package net.loyintean.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Hash Message Authentication Code
 * 散列消息鉴别码
 * <p>
 * 使用一个密钥，生成一个固定大小的小数据块（即MAC）。
 */
public class HmacUtils {

    /**
     * 从这个key可以看出，跟MD5有相似之处
     */
    private static final String KEY_MAC = "HmacMD5";

    /**
     * 初始化HAMC密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String initMacKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64Utils.encode(secretKey.getEncoded());
    }

    public static String encrypt(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        SecretKey secretKey = new SecretKeySpec(Base64Utils.decode(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return Base64Utils.encode(mac.doFinal());
    }
}
