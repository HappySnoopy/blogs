package net.loyintean.encrypt;

import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class EncryptTest {

    @Test
    public void testMd5() throws NoSuchAlgorithmException, IOException {
        String data = "123dfadf34r2i34cp9nqkdjx1ob234urh0vijfpasdiofhxn0134u9hvokdhfo1x7s;fij2-450v9sdufh01x34bfhoviufh01x7giausgdo1y8ri";
        String r = Md5Utils.encrypt(data);
        System.out.println(r.length() + " = " + r);
    }

    @Test
    public void testBase64() throws IOException {
        String r = Base64Utils.encode("abc");
        System.out.println(r.length() + " = " + r);

        String r1 = Base64Utils.decode2Str(r);

        System.out.println(r1.length() + " = " + r1);
        assertEquals(r, r1);

    }

    @Test
    public void testDes() {

        String data = "12345678";

        String result = DesUtils.encrypt(data);

        System.out.println(result.length() + " = " + result);

        String check = DesUtils.decrypt(result);
        System.out.println(check.length() + " = " + check);

        assertEquals(data, check);

    }

    @Test
    public void testAes() throws Exception {

        // 好像对长度有要求，不满足长度会做填充：4的倍数？
        String data = "1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd";

        String result = AesUtils.encrypt(data);

        System.out.println(result.length() + " = " + result);

        String check = AesUtils.decrypt(result);
        System.out.println(check.length() + " = " + check);

        assertEquals(data, check);

    }


    @Test
    public void testHmac() throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        String data = "123dfadf34r2i34cp9nqkdjx1ob234urh0vijfpasdiofhxn0134u9hvokdhfo1x7s;fij2-450v9sdufh01x34bfhoviufh01x7giausgdo1y8ri";
        String r = HmacUtils.encrypt(data, "123");
        System.out.println(r.length() + " = " + r);
    }

    @Test
    public void testSha() throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        String data = "123456678";
        String r = ShaUtils.encrypt(data);
        System.out.println(r.length() + " = " + r);

        r = ShaUtils.encrypt256(data);
        System.out.println(r.length() + " = " + r);
    }
}