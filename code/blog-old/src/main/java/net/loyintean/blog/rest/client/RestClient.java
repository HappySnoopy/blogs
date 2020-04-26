/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

/**
 * @author winters1224@163.com
 */
public interface RestClient {

    RestClient setUrl(String url);

    RestClient addHeader(String name, String value);

    RestClient addPathVariable(String name, String value);

    RestClient addRequestParam(String name, String value);

    RestClient setBody(Object body);

    RestClient responseAs(Class<?> type);

    <T> T get();

    <T> T post();

}