package com.airiea.openai.client;

import com.airiea.openai.model.enums.Role;
import com.airiea.openai.model.resource.Message;
import com.airiea.openai.model.operation.chat.ChatCompletion;
import com.airiea.openai.model.operation.chat.ChatCompletionRequest;
import com.airiea.openai.model.operation.embedding.EmbeddingRequest;
import com.airiea.openai.model.operation.embedding.EmbeddingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpenAClientTest {

    OpenAClient openAClient;

    @BeforeEach
    public void before() {
        openAClient = new OpenAClient("sk-");
    }

//    @Test
//    public void chatCompletion() {
//        Message message = new Message();
//        message.setRole(Role.user.name());
//        message.setContent("Hi, how are you?");
//
//        ChatCompletionRequest request = ChatCompletionRequest.builder()
//                .model("gpt-3.5-turbo")
//                .messages(Collections.singletonList(message)).build();
//
//        ChatCompletion chatCompletion = openAClient.createChatCompletion(request);
//        System.out.println(chatCompletion);
//        System.out.println(chatCompletion.getChoices().get(0));
//    }
//
//    @Test
//    public void embedding() {
//        List<String> embeddingInputList = new ArrayList<>();
//        embeddingInputList.add("Hi, how are you?");
//
//        EmbeddingRequest request = EmbeddingRequest.builder()
//                .input(embeddingInputList)
//                .model("text-embedding-ada-002").build();
//
//        EmbeddingResponse response = openAClient.createEmbeddings(request);
//        //System.out.println(response);
//        System.out.println(response.getData().get(0).getEmbedding().toString());
//    }
}
