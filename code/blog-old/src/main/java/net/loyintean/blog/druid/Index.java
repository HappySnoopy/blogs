/**
 * All Rights Reserved
 */
package net.loyintean.blog.druid;

import net.loyintean.blog.rest.client.RestClientFactory;

/**
 * @author linjun
 * @since 2017年9月25日
 *
 */
public class Index {

    private static final RestClientFactory factory = new RestClientFactory();
    static {
        Index.factory.setUserName("");
        Index.factory.setPassWord("@");
    }

    /**
     * @author linjun
     * @since 2017年9月25日
     * @param args
     */
    public static void main(String[] args) {
        try {


            String result = Index.factory.newClient().setUrl(
                "")
                .responseAs(String.class).get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
