/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

/**
 * @author winters1224@163.com
 */
public interface RestClient {

    public abstract RestClient setUrl(String url);

    public abstract RestClient addHeader(String name, String value);

    public abstract RestClient addPathVariable(String name, String value);

    public abstract RestClient addRequestParam(String name, String value);

    public abstract RestClient setBody(Object body);

    public abstract RestClient responseAs(Class<?> type);

    public abstract <T> T get();

    public abstract <T> T post();

}