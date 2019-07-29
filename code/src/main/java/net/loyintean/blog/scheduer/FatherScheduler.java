package net.loyintean.blog.scheduer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FatherScheduler {
    @Scheduled(cron = "0/10 * * * * ?")
    public void execute() {
        System.out.println(new Date() + " 执行任务的类是：" + this.getClass());
    }
}
