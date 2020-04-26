/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

/**
 * @author winters1224@163.com
 */
abstract class RestClient4Wrapper implements RestClient {

    /**
     * 所有实际操作都委托给它处理
     */
    protected RestClient delegator;

    /**
     * @return
     */
    @Override
    public <T> T get() {
        return this.delegator.get();
    }

    /**
     * @return
     */
    @Override
    public <T> T post() {
        return this.delegator.post();
    }

    /**
     * @param url
     * @return
     * @seealso {@link #delegator.setUrl(String)}
     */
    @Override
    public RestClient setUrl(String url) {
        return this.delegator.setUrl(url);
    }

    /**
     * @param name
     * @param value
     * @return
     * @seealso {@link #delegator.addHeader(String,String)}
     */
    @Override
    public RestClient addHeader(String name, String value) {
        return this.delegator.addHeader(name, value);
    }

    /**
     * @param name
     * @param value
     * @return
     * @seealso {@link #delegator.addPathVariable(String,String)}
     */
    @Override
    public RestClient addPathVariable(String name, String value) {
        return this.delegator.addPathVariable(name, value);
    }

    /**
     * @param name
     * @param value
     * @return
     * @seealso {@link #delegator.addRequestParam(String,String)}
     */
    @Override
    public RestClient addRequestParam(String name, String value) {
        return this.delegator.addRequestParam(name, value);
    }

    /**
     * @param body
     * @return
     * @seealso {@link #delegator.setBody(Object)}
     */
    @Override
    public RestClient setBody(Object body) {
        return this.delegator.setBody(body);
    }

    /**
     * @param type
     * @return
     * @seealso {@link #delegator.responseAs(Class)}
     */
    @Override
    public RestClient responseAs(Class<?> type) {
        return this.delegator.responseAs(type);
    }

}
