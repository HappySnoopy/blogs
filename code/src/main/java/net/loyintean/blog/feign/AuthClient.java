/**
 * All Rights Reserved
 */
package net.loyintean.blog.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rx.Observable;

/**
 * @author linjun
 * @since 2018年4月18日
 */
@FeignClient("thread-auth")
public interface AuthClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/thread_auth/rest/userInfos/vague")
    public List<Map<String, Object>> getUserInfosByNameVague(
            @RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam("enabled") Boolean enabled,
            @RequestHeader(name = "Authorization",
                    defaultValue = "Basic YWRtaW46c2VjMDA3dXjpdHk=") String authrization);

    @RequestMapping(method = RequestMethod.GET,
            value = "/thread_auth/rest/userInfos/vague")
    public Observable<List<Map<String, Object>>> getUserInfosByNameVague_toObservable(
            @RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam("enabled") Boolean enabled,
            @RequestHeader(name = "Authorization",
                    defaultValue = "Basic YWRtaW46c2VjMDA3dXjpdHk=") String authrization);

}
