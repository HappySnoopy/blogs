/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程安全
 *
 * @author Snoopy
 */
class RestClientAsDefault implements RestClient {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(RestClientAsDefault.class);

    /**
     * 只要创建后不修改此实例，则线程安全。
     * <p>
     * http://stackoverflow.com/questions/22989500/is-resttemplate-thread-safe
     */
    private final RestTemplate restTemplate;

    private String url;
    private Map<String, String> headers = new HashMap<>();
    private Object requestBody;
    private Map<String, Object> pathVariables = new HashMap<>();
    private Map<String, String> requestParams = new HashMap<>();
    private Class<?> responseType;

    RestClientAsDefault() {
        this(null);
    }

    RestClientAsDefault(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#setUrl(java.lang.String)
     */
    @Override
    public RestClient setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#addHeader(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public RestClient addHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#addPathVariable(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public RestClient addPathVariable(String name, String value) {
        this.pathVariables.put(name, value);
        return this;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#addRequestParam(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public RestClient addRequestParam(String name, String value) {
        this.requestParams.put(name, value);
        return this;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#setBody(java.lang.Object)
     */
    @Override
    public RestClient setBody(Object body) {
        this.requestBody = body;
        return this;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#responseAs(java.lang.Class)
     */
    @Override
    public RestClient responseAs(Class<?> type) {
        this.responseType = type;
        return this;
    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#get()
     */
    @Override
    public <T> T get() {

        String urlWithParams = this.parseUrlWithParams();

        RestClientAsDefault.LOGGER.debug(
            "urlWithParams={},responseType={},pathVariables={}", urlWithParams,
            this.responseType, this.pathVariables);
        T result = (T) this.restTemplate.getForObject(urlWithParams,
            this.responseType, this.pathVariables);

        return result;

    }

    /**
     * @see net.loyintean.blog.rest.client.RestClient#post()
     */
    @Override
    public <T> T post() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(this.headers);

        HttpEntity<Object> request = new HttpEntity<>(this.requestBody,
            httpHeaders);

        String urlWithParams = this.parseUrlWithParams();
        RestClientAsDefault.LOGGER.debug(
            "urlWithParams={},request={},responseType={},pathVariables={}",
            urlWithParams, request, this.responseType, this.pathVariables);

        T result = (T) this.restTemplate.postForObject(urlWithParams, request,
            this.responseType, this.pathVariables);

        return result;
    }

    private String parseUrlWithParams() {
        StringBuilder urlWithParams = new StringBuilder(30).append(this.url);

        if (!this.requestParams.isEmpty()) {
            urlWithParams.append('?');

            for (String paramKey : this.requestParams.keySet()) {
                urlWithParams.append(paramKey).append('=')
                    .append(this.requestParams.get(paramKey)).append('&');
            }
        }
        return urlWithParams.toString();
    }
}
