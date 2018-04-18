/**
 * All Rights Reserved
 */
package net.loyintean.blog.feign;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rx.Observable;
import rx.Observer;

/**
 * @author linjun
 * @since 2018年4月18日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeignApplication.class)
public class AuthClientTest {

    @Autowired
    private AuthClient authClient;

    //    @Test
    public void test() {

        List<Map<String, Object>> list = this.authClient
            .getUserInfosByNameVague("admin", null, null,
                "Basic YWRtaW46c2VjMDA3dXJpdHk=");

        System.out.println(list);

    }

    @Test
    public void test_obserable() throws InterruptedException {

        Observable<List<Map<String, Object>>> list = this.authClient
            .getUserInfosByNameVague_toObservable("admin", null, null,
                "Basic YWRtaW46c2VjMDA3dXJpdHk=");
        System.out.println(Thread.currentThread().getName() + "   "
            + Instant.now() + "  " + list);

        AtomicBoolean flag = new AtomicBoolean(true);

        list.subscribe(new Observer<List<Map<String, Object>>>() {

            @Override
            public void onCompleted() {
                flag.set(false);
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Map<String, Object>> t) {
                System.out.println(Thread.currentThread().getName() + "   "
                    + Instant.now() + "  " + t);
            }
        });

        while (flag.get()) {
            System.out.println(
                Thread.currentThread().getName() + "   " + "这里在做一些其它的事情。");
            Thread.sleep(1000l);
        }

        System.out.println("全部任务完成");

    }
}
