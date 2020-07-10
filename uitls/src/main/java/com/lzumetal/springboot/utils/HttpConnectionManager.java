package com.lzumetal.springboot.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-07-09
 */
public class HttpConnectionManager {

    /* 客户端和服务器建立连接的超时时间 */
    private static final int CONNECT_TIMEOUT = 2000;

    /* 从连接池获取连接的超时时间*/
    private static final int CONNECTION_REQUESTT_IMEOUT = 1000;

    /* 客户端从服务器读取数据的超时时间 */
    private static final int SOCKET_TIMEOUT = 15000;

    private PoolingHttpClientConnectionManager cm;

    private CloseableHttpClient httpClient;

    public static class LazyHolder {
        static final HttpConnectionManager instance = new HttpConnectionManager();
    }

    public static HttpConnectionManager getInst() {
        return LazyHolder.instance;
    }

    private HttpConnectionManager() {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(500);// 每个主机的最大并行链接数
        cm.setDefaultMaxPerRoute(3000);// 客户端总并行链接最大数

        //开启一个线程，定期清理连接，每隔5秒清理一次
        ScheduledExecutorService monitorExecutor = Executors.newSingleThreadScheduledExecutor();
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    // Close expired connections
                    cm.closeExpiredConnections();
                    // Optionally, close connections
                    // that have been idle longer than 30 sec
                    cm.closeIdleConnections(30, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000, 5000, TimeUnit.MILLISECONDS);

        //HttpClient线程安全，创建唯一的HttpClient实例
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(cm);
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    // 如果keep-alive值没有由服务器明确设置，那么保持连接持续5秒。
                    keepAlive = 5000;
                }
                return keepAlive;
            }
        });

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUESTT_IMEOUT)
                .build();
        httpClient = httpClientBuilder
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
    }

    CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
