/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.loyintean.blog.repay.Result4Repay;

/**
 * @author linjun
 * @since 2017年9月22日
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/timeout", method = RequestMethod.GET)
    @ResponseBody
    public Result4Repay timeOut() {

        System.out.println("timeout start");
        try {
            Thread.sleep(1000000000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }
}
