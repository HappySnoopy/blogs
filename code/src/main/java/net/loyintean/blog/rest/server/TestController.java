/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.server;

import net.loyintean.blog.repay.Result4Calculate;
import net.loyintean.blog.repay.Result4Repay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
        Result4Repay result4Repay = new Result4Repay();
        result4Repay.setOverdue(new Result4Calculate());
        result4Repay.setCurrent(new Result4Calculate());
        result4Repay.setAdvance(new Result4Calculate());
        result4Repay.setBalance(BigDecimal.ZERO);
        result4Repay.setFrozenBalance(BigDecimal.ZERO);
        result4Repay.setTotal(BigDecimal.ZERO);
        result4Repay.setCanDoAdvanceA(new Result4Calculate());

        return result4Repay;

    }
}
