package com.airiea.openai.client;

import com.airiea.openai.model.operation.chat.ChatCompletion;
import com.airiea.openai.model.operation.chat.ChatCompletionRequest;
import com.airiea.openai.model.operation.embedding.EmbeddingRequest;
import com.airiea.openai.model.operation.embedding.EmbeddingResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * <a href="https://beta.openai.com/docs/api-reference/">...</a>
 */
public interface OpenAiApi {

    @POST("/v1/chat/completions")
    Single<ChatCompletion> createChatCompletion(@Body ChatCompletionRequest request);

    @POST("/v1/embeddings")
    Single<EmbeddingResponse> createEmbeddings(@Body EmbeddingRequest request);
}
