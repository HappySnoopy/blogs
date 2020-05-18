package net.loyintean.springmvcbase.monitor.service.impl;

import net.loyintean.springmvcbase.monitor.service.MonitorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class MonitorService4StartupImpl implements MonitorService {

    private static final LocalDateTime START_UP = LocalDateTime.now();

    @Override
    public Object monitor() {
        return "服务已启动；启动时间为：" + START_UP;
    }
}
