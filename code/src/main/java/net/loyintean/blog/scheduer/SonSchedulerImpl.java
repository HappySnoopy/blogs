package net.loyintean.blog.scheduer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The type Son scheduler.
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2019 -07-29
 */
@Component
public class SonSchedulerImpl extends FatherScheduler {
    /**
     * 在4.1.6.RELEASE版本里，ms子类会用父类的Scheduled注解多注册一次。导致子类按两个cron去执行了。
     *
     * 这个项目用的是5.x的版本，没有发现这个问题
     */
    @Override
    @Scheduled(cron = "1/10 * * * * ?")
    public void execute() {
        System.out.println(new Date() + " 执行任务的类是：" + this.getClass());
    }
}
