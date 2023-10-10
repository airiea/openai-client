package com.airiea.openai.client;

import com.airiea.openai.client.interceptor.AuthenticationInterceptor;
import com.airiea.openai.model.operation.chat.ChatCompletion;
import com.airiea.openai.model.operation.chat.ChatCompletionRequest;
import com.airiea.openai.model.operation.embedding.EmbeddingRequest;
import com.airiea.openai.model.operation.embedding.EmbeddingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://beta.openai.com/docs/api-reference/">...</a>
 */
public class OpenAClient {
    private static final String BASE_URL = "https://api.openai.com/";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private final OpenAiApi api;
    private final ExecutorService executorService;

    public OpenAClient(final String token) {
        ObjectMapper mapper = new ObjectMapper();
        OkHttpClient client = defaultClient(token);
        Retrofit retrofit = defaultRetrofit(client, mapper);

        this.api = retrofit.create(OpenAiApi.class);
        this.executorService = client.dispatcher().executorService();
    }

    private static OkHttpClient defaultClient(String token) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(token))
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(OpenAClient.DEFAULT_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS)
                .build();
    }

    private static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static <T> T execute(Single<T> apiCall) {
        try {
            return apiCall.blockingGet();
        } catch (HttpException e) {
            throw new CompletionException("Failed to complete open ai chat completion task: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalStateException("Openai API call failed.", e);
        }
    }

    public ChatCompletion createChatCompletion(ChatCompletionRequest request) {
        return execute(api.createChatCompletion(request));
    }

    public EmbeddingResponse createEmbeddings(EmbeddingRequest request) {
        return execute(api.createEmbeddings(request));
    }

}
