package indi.cloud.view.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class AuthenticationInterceptorOpenApi implements ClientHttpRequestInterceptor {

    private static String token;

    @Value("${default-token}")
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();

        // 加入自定义字段
        headers.add("api_token", getApiToken());

        // 保证请求继续被执行
        return execution.execute(request, body);
    }

    private String getApiToken() {

        StringBuilder result = new StringBuilder();
        try {
            File file = new File(token);
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append( s);
            }
            br.close();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}