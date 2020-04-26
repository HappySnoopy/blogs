/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 用于异步操作的rest客户端
 *
 * @author winters1224@163.com
 */
public class RestClient4Async extends RestClient4Wrapper {

    /**
     * 多线程执行器
     */
    private ExecutorService executorService;

    /**
     */
    RestClient4Async(RestClient restClient, ExecutorService executorService) {
        super();
        this.delegator = restClient;
        this.executorService = executorService;
    }

    /**
     * 异步的get方法
     *
     * @return
     */
    public <T> Future<T> getAsync() {
        Future<T> result = this.executorService.submit(new Callable<T>() {
            @Override
            public T call() {
                return RestClient4Async.this.delegator.get();
            }
        });
        return result;
    }

    /**
     * 异步的post方法
     *
     * @return
     */
    @Async
    public <T> Future<T> postAsync() {
        Future<T> result = this.executorService.submit(new Callable<T>() {
            @Override
            public T call() {
                return RestClient4Async.this.delegator.post();
            }
        });
        return result;
    }

    // winters1224@163.com 2016-04-05 很惊讶，返回类型不一样，居然没有出现编译问题？
    /**
     * @param url
     * @return
     * @seealso {@link #delegator.setUrl(String)}
     */
    @Override
    public RestClient4Async setUrl(String url) {
        this.delegator.setUrl(url);
        return this;

    }

    /**
     * @param name
     * @param value
     * @return
     * @seealso {@link #delegator.addHeader(String,String)}
     */
    @Override
    public RestClient4Async addHeader(String name, String value) {
        this.delegator.addHeader(name, value);
        return this;
    }

    /**
     * @param name
     * @param value
     * @return
     * @seealso {@link #delegator.addPathVariable(String,String)}
     */
    @Override
    public RestClient4Async addPathVariable(String name, String value) {
        this.delegator.addPathVariable(name, value);
        return this;
    }

    /**
     * @param name
     * @param value
     * @return
     * @seealso {@link #delegator.addRequestParam(String,String)}
     */
    @Override
    public RestClient4Async addRequestParam(String name, String value) {
        this.delegator.addRequestParam(name, value);
        return this;
    }

    /**
     * @param body
     * @return
     * @seealso {@link #delegator.setBody(Object)}
     */
    @Override
    public RestClient4Async setBody(Object body) {
        this.delegator.setBody(body);
        return this;
    }

    /**
     * @param type
     * @return
     * @seealso {@link #delegator.responseAs(Class)}
     */
    @Override
    public RestClient4Async responseAs(Class<?> type) {
        this.delegator.responseAs(type);
        return this;
    }

}
