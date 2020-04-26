package net.loyintean.blog.ip138;

import net.loyintean.blog.rest.client.RestClientFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 单线程版本
 *
 * <ul>修改多线程的话需要注意：
 * <li>http连接池的复用性。包括总的连接数和每域名连接数</li>
 * <li>各类操作、字段的线程安全性。如
 * <ul>Http链接是无状态的，因此可以认为是安全的</ul>
 * <ul>HttpParser的解析器目测不是线程安全的；</ul>
 * <ul>CallBack由于有状态，因此不安全</ul>
 * <ul>MobileData肯定不安全；</ul>
 * <ul>FileWriter是安全的</ul></li>
 * <li>各类资源如何关闭。尤其是要避免操作还没结束、资源就关闭了的情况。</li>
 * <li>线程池大小：Http链接消耗网络IO；解析内容消耗CPU；写文件消耗磁盘IO。</li>
 * </ul>
 *
 * @author 林俊
 */
public class Spider4Ip138 {

    /**
     * Request URL: http://www.ip138.com:8080/search.asp?mobile=13542156578&action=mobile
     */
    private static final String URL_IP_138 = "http://www.ip138.com:8080/search.asp";

    /**
     * The constant Factory.
     */
    private static final RestClientFactory Factory = new RestClientFactory();

    /**
     * The constant parser.注意是线程不安全的。
     */
    private static DocumentParser parser;

    static {
        try {
            parser = new DocumentParser(DTD.getDTD(""));
        } catch (IOException e) {
            e.printStackTrace();
            parser = null;
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 单线程可以共用一套数据，减少内存消耗
        Ip138Data4Mobile mobileData = new Ip138Data4Mobile();
        Ip138Callback callback = new Ip138Callback();
        callback.setMobile(mobileData);

        try (FileWriter writer = new FileWriter(new File("C:\\Users\\LAP02F2170925\\Desktop\\ip138.txt"))) {

            StringBuilder fileContext = new StringBuilder(512);
            fileContext.append("手机号").append("\t").append("归属地").append("\t").append("类型").append("\t").append("区号")
                    .append("\t").append("邮编").append(System.lineSeparator());
            writer.append(fileContext);

            for (long mobileNo = 13488717901L; mobileNo < 13488717911L; mobileNo++) {
                mobileData.setMobile(mobileNo);
                String result = callIp138(mobileNo);
                parseInfo(result, callback);
                write(writer, mobileData);
                System.out.println("处理完毕：mobileData=" + mobileData);
            }
        } catch (Exception e) {
            // /html/body/table[2]/tr[3]/td[2]
            e.printStackTrace();
        }
    }

    /**
     * Call ip 138 string.
     *
     * @param mobileNo the mobile no
     * @return the string
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    private static String callIp138(long mobileNo) throws UnsupportedEncodingException {
        String result = Spider4Ip138.Factory.newClient().setUrl(URL_IP_138).addRequestParam("action", "mobile")
                .addRequestParam("mobile", Long.toString(mobileNo)).responseAs(String.class).addHeader("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive").addHeader("Host", "www.ip138.com")
                .addHeader("Upgrade-Insecure-Requests", "1").addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                .addHeader("Content-Type", "text/html; charset=gb2312").get();

        // 默认是ISO-8859-1，页面指定为GB-2312的，需要转一次编码
        result = new String(result.getBytes(StandardCharsets.ISO_8859_1), "GB2312");

        return result;
    }

    /**
     * Parse info.
     *
     * @param html     the html
     * @param callback the callback
     * @throws IOException the io exception
     */
    private static void parseInfo(String html, Ip138Callback callback) throws IOException {
        try (BufferedReader reader = new BufferedReader(new CharArrayReader(html.toCharArray()))) {
            parser.parse(reader, callback, true);
        }
    }

    /**
     * Write.
     *
     * @param writer     the writer
     * @param mobileData the mobile data
     * @throws IOException the io exception
     */
    private static void write(FileWriter writer, Ip138Data4Mobile mobileData) throws IOException {
        StringBuilder fileContext = new StringBuilder(512);
        fileContext.append(mobileData.getMobile()).append("\t").append(mobileData.getAddress()).append("\t")
                .append(mobileData.type).append("\t").append(mobileData.areaCode).append("\t")
                .append(mobileData.postCode).append(System.lineSeparator());
        writer.append(fileContext);

    }

    /**
     * The type Ip138Callback.
     *
     * @author 林俊
     */
    public static class Ip138Callback extends HTMLEditorKit.ParserCallback {
        /**
         * The Mobile.
         */
        private Ip138Data4Mobile mobile;
        /**
         * The Is address.
         */
        private boolean isAddress = false;
        /**
         * The Is type.
         */
        private boolean isType = false;
        /**
         * The Is area code.
         */
        private boolean isAreaCode = false;
        /**
         * The Is post code.
         */
        private boolean isPostCode = false;

        @Override
        public void handleText(char[] data, int pos) {
            // 这一串儿的字符串操作看着真是……扎眼
            String target = new String(data).replaceAll("&nbsp;", "").trim();
            if (isAddress) {
                mobile.setAddress(target);
                isAddress = false;
            }
            if (isType) {
                mobile.setType(target);
                isType = false;
            }
            if (isAreaCode) {
                mobile.setAreaCode(target);
                isAreaCode = false;
            }
            if (isPostCode) {
                mobile.setPostCode(target);
                isPostCode = false;
            }
            switch (target) {
                case "卡号归属地":
                    isAddress = true;
                    break;
                case "卡类型":
                    isType = true;
                    break;
                case "区 号":
                    isAreaCode = true;
                    break;
                case "邮 编":
                    isPostCode = true;
                    break;
            }
        }

        /**
         * Gets mobile.
         *
         * @return the mobile
         */
        public Ip138Data4Mobile getMobile() {
            return mobile;
        }

        /**
         * Sets mobile.
         *
         * @param mobile the mobile
         */
        public void setMobile(Ip138Data4Mobile mobile) {
            this.mobile = mobile;
        }
    }

    /**
     * The type Ip138Data4Mobile.
     *
     * @author 林俊
     */
    public static class Ip138Data4Mobile {
        /**
         * The Mobile.
         */
        long mobile;
        /**
         * The Address.
         */
        String address;
        /**
         * The Type.
         */
        String type;
        /**
         * The Area code.
         */
        String areaCode;
        /**
         * The Post code.
         */
        String postCode;

        /**
         * Gets mobile.
         *
         * @return the mobile
         */
        public long getMobile() {
            return mobile;
        }

        /**
         * Sets mobile.
         *
         * @param mobile the mobile
         */
        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        /**
         * Gets address.
         *
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * Sets address.
         *
         * @param address the address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * Gets type.
         *
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * Sets type.
         *
         * @param type the type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Gets area code.
         *
         * @return the area code
         */
        public String getAreaCode() {
            return areaCode;
        }

        /**
         * Sets area code.
         *
         * @param areaCode the area code
         */
        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        /**
         * Gets post code.
         *
         * @return the post code
         */
        public String getPostCode() {
            return postCode;
        }

        /**
         * Sets post code.
         *
         * @param postCode the post code
         */
        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
        }
    }
}
