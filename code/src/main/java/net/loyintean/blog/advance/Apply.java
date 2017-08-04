/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.advance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.loyintean.blog.rest.client.RestClientFactory;

/**
 * @author linjun
 * @since 2017年5月22日
 */
public class Apply {

    private static final RestClientFactory factory = new RestClientFactory();
    static {
        Apply.factory.setUserName("10020865");
        Apply.factory.setPassWord("");
    }


    private static final List<String> URL = Arrays.asList();

    private static final List<String> BODY = Arrays
        .asList("{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-10\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-24\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-04-26\"}",
            "{\"repayDay\":\"2017-04-28\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-16\"}", "{\"repayDay\":\"2017-04-19\"}",
            "{\"repayDay\":\"2017-05-17\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-06\"}", "{\"repayDay\":\"2017-05-08\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-08\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-16\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-10\"}",
            "{\"repayDay\":\"2017-05-18\"}", "{\"repayDay\":\"2017-05-18\"}",
            "{\"repayDay\":\"2017-04-22\"}", "{\"repayDay\":\"2017-04-28\"}",
            "{\"repayDay\":\"2017-04-29\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-30\"}", "{\"repayDay\":\"2017-04-30\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-04-29\"}",
            "{\"repayDay\":\"2017-05-11\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-11\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-12\"}", "{\"repayDay\":\"2017-05-12\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-06\"}",
            "{\"repayDay\":\"2017-05-07\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-12\"}",
            "{\"repayDay\":\"2017-04-22\"}", "{\"repayDay\":\"2017-04-22\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-04-29\"}",
            "{\"repayDay\":\"2017-05-21\"}", "{\"repayDay\":\"2017-04-27\"}",
            "{\"repayDay\":\"2017-04-28\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-04-22\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-04\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-05-18\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-04-23\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-04-26\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-05-03\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-26\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-03\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-02\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-04-23\"}",
            "{\"repayDay\":\"2017-05-01\"}", "{\"repayDay\":\"2017-05-07\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-02\"}",
            "{\"repayDay\":\"2017-05-02\"}", "{\"repayDay\":\"2017-05-02\"}",
            "{\"repayDay\":\"2017-05-15\"}", "{\"repayDay\":\"2017-05-02\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-07\"}",
            "{\"repayDay\":\"2017-05-02\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-05\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-10\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-21\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-09\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-16\"}",
            "{\"repayDay\":\"2017-05-15\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-24\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-30\"}",
            "{\"repayDay\":\"2017-04-28\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-06\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-04-30\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-05-15\"}", "{\"repayDay\":\"2017-05-15\"}",
            "{\"repayDay\":\"2017-05-01\"}", "{\"repayDay\":\"2017-05-08\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-04-26\"}",
            "{\"repayDay\":\"2017-05-20\"}", "{\"repayDay\":\"2017-05-12\"}",
            "{\"repayDay\":\"2017-05-20\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-04-27\"}", "{\"repayDay\":\"2017-05-03\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-18\"}",
            "{\"repayDay\":\"2017-05-12\"}", "{\"repayDay\":\"2017-04-30\"}");

    private static final String REPAY_URL = "";

    /*
     * "{\"lrId\":816452,\"bookDate\":\"2017-05-19\"}",
     * "{\"lrId\":855308,\"bookDate\":\"2017-04-19\"}",
     * "{\"lrId\":1024455,\"bookDate\":\"2017-04-24\"}"
     */
    private static final List<String> REPAY_BODY = Arrays
        .asList("{\"lrId\":816452,\"bookDate\":\"2017-05-19\"}",
            "{\"lrId\":855308,\"bookDate\":\"2017-04-19\"}");
    /**
     * @author linjun
     * @since 2017年5月22日
     * @param args
     */
    public static void main(String[] args) {

        for (int index = 0; index < Apply.REPAY_BODY.size(); index++) {

            try {

                Map<String, Object> result;

                //            result = Apply.factory.newClient()
                //                .setUrl(Apply.URL.get(index)).responseAs(Map.class)
                //                .setBody(Apply.BODY.get(index))
                //                .addHeader("content-type", "application/json").post();
                //            System.out.println(result);

                result = Apply.factory.newClient().setUrl(Apply.REPAY_URL)
                    .responseAs(Map.class).setBody(Apply.REPAY_BODY.get(index))
                    .addHeader("content-type", "text/html").post();
                System.out.println(result);
            System.out.println(Apply.REPAY_BODY.get(index));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
