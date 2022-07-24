package net.loyintean.encrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesUtils {

    private static final String ALGORITHM = "AES";

    /**
     * AES/CBC/NOPadding
     * AES：默认模式
     * CBC：初始化Cipher对象时，需要增加参数“初始化向量IV：IvParameterSpec(Key.getByts())”
     * NoPadding：使用NoPadding模式时，原文长度必须是8byte的整数倍
     */
    private static final String TRANS = "AES";

    private static final String KEY = "1234567887654321";

    public static String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
//        获取Cipher
        Cipher cipher = Cipher.getInstance(TRANS);
//        生成秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
//        指定模式（加密）和密钥
//        创建初始化向量(CBC模式下）
//        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] result = cipher.doFinal(Base64Utils.decode(data));
        return Base64Utils.encode(result);
    }

    public static String decrypt(String data) throws Exception {
//        获取Cipher
        Cipher cipher = Cipher.getInstance(TRANS);
//        生成秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
//        指定模式（加密）和密钥
//        创建初始化向量(CBC模式下）
//        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        解密
        byte[] result = cipher.doFinal(Base64Utils.decode(data));
        return Base64Utils.encode(result);
    }
}
