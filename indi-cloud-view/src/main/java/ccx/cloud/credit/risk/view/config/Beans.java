package ccx.cloud.credit.risk.view.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;

@Component
public class Beans {

    @Resource
    AuthenticationInterceptorOpenApi authenticationInterceptorOpenApi;


    @Bean(name = "connectionManager")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        // 总连接数
        manager.setMaxTotal(1000);
        // 同路由的并发数
        manager.setDefaultMaxPerRoute(1000);
        // 总数
        manager.setMaxTotal(1000);
        return manager;
    }

    @Bean(name = "httpClient")
    public HttpClient httpClient(PoolingHttpClientConnectionManager connectionManager) {

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(connectionManager);
        // 重试3次
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

        return httpClientBuilder.build();
    }

    @Bean(name = "requestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 5秒连接超时
        requestFactory.setConnectTimeout(5 * 1000);
        // 30秒读取超时
        requestFactory.setReadTimeout(30 * 1000);
        return requestFactory;
    }

    @Bean("restTemplate")
    @LoadBalanced
    public RestTemplate localRestTemplate(HttpComponentsClientHttpRequestFactory requestFactory) {

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // API，需要拦截处理
        restTemplate.setInterceptors(Collections.singletonList(authenticationInterceptorOpenApi));
        return restTemplate;
    }

}
