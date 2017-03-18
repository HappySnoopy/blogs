/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import net.loyintean.blog.rest.RetryConfiguration;

/**
 * @author winters1224@163.com
 */
public class RestClientFactory {

    /**
     * 只要创建后不修改此实例，则线程安全。
     * <p>
     * http://stackoverflow.com/questions/22989500/is-resttemplate-thread-safe
     */
    private static RestTemplate restTemplate;

    private int connectionTimeout = -1;
    private int socketTimeout = -1;
    protected String userName;
    protected String passWord;

    /**
     * 重试配置
     */
    private RetryConfiguration retryConfiguration;

    /**
     * 由于这个类不是直接注入，而是用Factory手动生成的，因此用不了Async注解
     */
    private ExecutorService executorService = Executors
        .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 会尝试初始化 {@link #restTemplate}，并用它来实例化一个RestClient
     *
     * @return
     */
    public RestClient newClient() {
        RestClient client = this.newBasicClient();

        if (this.retryConfiguration != null) {
            client = new RestClient4Retry(client, this.retryConfiguration);
        }

        return client;
    }

    /**
     * 获取一个异步的rest客户端
     *
     * @return
     */
    public RestClient4Async newAsyncClient() {
        RestClient4Async async = new RestClient4Async(this.newClient(),
            this.executorService);
        return async;
    }

    /**
     * 会尝试初始化 {@link #restTemplate}，并用它来实例化一个RestClient
     *
     * @return
     */
    private RestClient newBasicClient() {
        if (RestClientFactory.restTemplate == null) {
            synchronized (this) {
                if (RestClientFactory.restTemplate == null) {
                    RestClientFactory.restTemplate = new RestTemplate(
                        new HttpComponentsClientHttpRequestFactory(
                            this.buildHttpClient().build()));
                   RestClientFactory.restTemplate.getMessageConverters()
                        .add(new StringHttpMessageConverter());
                }
            }
        }
        return new RestClientAsDefault(RestClientFactory.restTemplate);
    }

    public RestClientFactory retry(RetryConfiguration retryConfiguration1) {
        this.retryConfiguration = retryConfiguration1;
        return this;
    }

    protected SchemePortResolver getSchemePortResolver() {
        return DefaultSchemePortResolver.INSTANCE;
    }

    /**
     * THREAD-10685 修改private -> protected
     * 有资源管理问题。<br>
     * 有默认的ssl域名管理
     *
     * @return
     */
    @SuppressWarnings({ "resource", "deprecation" })
    protected HttpClientBuilder buildHttpClient() {
        // increase connections for route and total.
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
        poolingHttpClientConnectionManager.setMaxTotal(200);

        HttpClientBuilder cb = HttpClientBuilder.create()
            .disableAutomaticRetries()
            .setConnectionManager(poolingHttpClientConnectionManager)
            // 重用连接，nginx reload会报错！！！
            .setSSLHostnameVerifier(new DefaultHostnameVerifier())
            .setConnectionReuseStrategy(new NoConnectionReuseStrategy())
            .setSchemePortResolver(this.getSchemePortResolver())
            .setDefaultRequestConfig(
                RequestConfig.custom()
                    .setConnectTimeout(this.getConnectionTimeout())
                    .setSocketTimeout(this.getSocketTimeout()).build());

        try {
            // set ssl context
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0,
                        String arg1) {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0,
                        String arg1) {
                }
            } }, null);

            cb.setSslcontext(ctx);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }

        if (this.getUserName() != null && this.getPassWord() != null) {
            CredentialsProvider cp = new BasicCredentialsProvider();
            cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(
                this.getUserName(), this.getPassWord()));
            cb.setDefaultCredentialsProvider(cp);
        }
        return cb;

    }

    /**
     * @param connectionTimeout
     *        the {@link #connectionTimeout} to set
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * @param socketTimeout
     *        the {@link #socketTimeout} to set
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * @param userName
     *        the {@link #userName} to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param passWord
     *        the {@link #passWord} to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * @return the {@link #connectionTimeout}
     */
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    /**
     * @return the {@link #socketTimeout}
     */
    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    /**
     * @return the {@link #userName}
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * @return the {@link #passWord}
     */
    public String getPassWord() {
        return this.passWord;
    }

    /**
     * @return the {@link #retryConfiguration}
     */
    public RetryConfiguration getRetryConfiguration() {
        return this.retryConfiguration;
    }

    /**
     * @return the {@link #executorService}
     */
    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    /**
     * @param retryConfiguration
     *        the {@link #retryConfiguration} to set
     */
    public void setRetryConfiguration(RetryConfiguration retryConfiguration) {
        this.retryConfiguration = retryConfiguration;
    }

    /**
     * @param executorService
     *        the {@link #executorService} to set
     */
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
