package net.loyintean.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;

public class DesUtils {

    private static Key key;

    private static String KEY_STR = "MY_KEY";
    private static String CHARSET_NAME = "UTF-8";
    private static String ALGORITHM = "DES";

    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 使用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置秘钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成秘钥对象
            key = generator.generateKey();
            // 丢弃生成器
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String str) {
        try {
            // utf8编码
            byte[] bytes = Base64Utils.decode(str);
            System.out.println(Arrays.toString(bytes));
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(bytes);
            // 编码返回
            return Base64Utils.encode(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String str) {
        try {
            byte[] bytes = Base64Utils.decode(str);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(bytes);
            System.out.println(Arrays.toString(result));
            return Base64Utils.encode(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
