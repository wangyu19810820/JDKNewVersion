package jdk11;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HttpClientTest {

    @Test
    public void test1() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.baidu.com/")).build();
        BodyHandler<String> bodyHandler = BodyHandlers.ofString();
        // 同步请求
        HttpResponse<String> response = client.send(request, bodyHandler);
        String body = response.body();
        System.out.println(body);
    }

    @Test
    public void test2() throws IOException, InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.baidu.com/")).build();
        BodyHandler<String> bodyHandler = BodyHandlers.ofString();
        // 异步请求
        CompletableFuture<HttpResponse<String>> sendAsync = client.sendAsync(request, bodyHandler);
        HttpResponse<String> response = sendAsync.get();
        String body = response.body();
        System.out.println(body);
    }
}
