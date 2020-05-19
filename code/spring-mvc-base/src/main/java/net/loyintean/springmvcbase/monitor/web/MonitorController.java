package net.loyintean.springmvcbase.monitor.web;

import lombok.extern.slf4j.Slf4j;
import net.loyintean.springmvcbase.common.exception.BizException;
import net.loyintean.springmvcbase.monitor.bean.MonitorResult;
import net.loyintean.springmvcbase.monitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping()
@Slf4j
public class MonitorController {

    @Autowired
    private Collection<MonitorService> monitorServices;

    @GetMapping("/monitor")
    @ResponseBody
    public MonitorResult mointor() {
        return monitorServices.stream().map(MonitorService::monitor)
                .collect(MonitorResult::new, MonitorResult::add, MonitorResult::add);
    }

    @GetMapping("/exception")
    @ResponseBody
    public MonitorResult exception() {
        throw new RuntimeException("测试系统异常");
    }

    @GetMapping("/bizException")
    @ResponseBody
    public MonitorResult bizException() {
        throw new BizException("1001", "测试业务异常");
    }

}
