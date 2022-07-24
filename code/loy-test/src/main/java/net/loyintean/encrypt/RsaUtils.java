package net.loyintean.encrypt;

import java.security.*;

/**
 * RSA是一种非对称加密算法，需要两个密钥：一个用来加密，另一个用来解密。
 * 目前它是最有影响力、最常用的、也是普遍认为最优秀的公钥方案之一。
 * <p>
 * 该算法基于一个数论事实：将两个大质数相乘十分容易；但是想要对其乘积进行因式分解却极其困难。因此，可以将乘积公开作为加密密钥。
 * 由于进行的都是大数计算，RAS最快也比DES慢好几倍，比相同安全级别的对称密码算法要慢1000倍左右。
 * 所以RAS一般只用做少量数据加密。
 * <p>
 * 主要步骤有：
 * 1. 生成密钥对
 * 2. 使用公钥加密、私钥解密
 * 3. 使用私钥加密、公钥解密
 */
public class RsaUtils {

    /**
     * 生成密钥对，并保存在文件中
     *
     * @param algorithm 算法
     * @param pubPath   公钥保存路径
     * @param priPath   私钥保存路径
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws NoSuchAlgorithmException {
// 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
//         获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        获取公钥
        PublicKey publicKey = keyPair.getPublic();
//        获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
//        获取byte[]数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
//        进行base64编码
        String publicKeyStr = Base64Utils.encode(publicKeyEncoded);
        String privateKeyStr = Base64Utils.encode(privateKeyEncoded);

//        保存文件
        // TODO linjun 2022-07-24
    }


}
