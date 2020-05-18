package net.loyintean.springmvcbase.web;

import lombok.extern.slf4j.Slf4j;
import net.loyintean.springmvcbase.bean.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/monitor")
@Slf4j
public class MonitorController {

    private static final Date START_UP = new Date();

    @GetMapping({"/init", "/", "test"})
    @ResponseBody
    public MonitorResult mointor() {
        return new MonitorResult();
    }

    public static class MonitorResult extends BaseResult<String> {

        private MonitorResult() {
            super(SUCCESS, null, "服务启动。启动时间：" + START_UP);
        }
    }
}
