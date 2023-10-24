package com.airiea.openai.client;

import com.airiea.openai.model.enums.Role;
import com.airiea.openai.model.resource.Message;
import com.airiea.openai.model.operation.chat.ChatCompletion;
import com.airiea.openai.model.operation.chat.ChatCompletionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OpenAiClientTest {

    OpenAiClient openAiClient;

    @BeforeEach
    public void before() {
        openAiClient = new OpenAiClient("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");
    }

    @Test
    public void chatCompletion() {
        List<Message> messages = new ArrayList<>();
        Message systemMessage = new Message();
        systemMessage.setRole(Role.system.name());
        systemMessage.setContent("Your role is to:Concisely extract relevant information from the user's input.Identify pieces of information that relate to the subject.Cleanup duplicate data or irrelevant context.Respond only with refined context in the specified format.");
        messages.add(systemMessage);

        Message asisMessage = new Message();
        asisMessage.setRole(Role.assistant.name());
        asisMessage.setContent("You will provide input text that contains information about a subject. This text may have unnecessary context, redundancies, or details. Ensure that the goals are optimally aligned with the successful completion of its assigned task. Extract and refine the data to provide the most relevant and concise information about the subject.");
        messages.add(asisMessage);

        Message asisMessageExampleInput = new Message();
        asisMessageExampleInput.setRole(Role.assistant.name());
        asisMessageExampleInput.setContent("Given the input text:\n" +
                "As mentioned above, Yifan's has a clear has interest in what's happening in the ML tech space and that is a strength. He should try to identify actionable ways that his insights and ideas can be applied at Audible. Gave a talk on generative AI which was introductory, but it would be great to have Yifan produce concrete examples of how we can apply it to our business.");
        messages.add(asisMessageExampleInput);

        Message asisMessageExampleOutput = new Message();
        asisMessageExampleOutput.setRole(Role.assistant.name());
        asisMessageExampleOutput.setContent("[\"Yifan has a clear interest in the ML tech space.\",\"Yifan's interest in ML is a strength.\",\"Yifan should identify actionable ways to apply insights at Audible.\",\"Yifan gave a talk on generative AI.\",\"The talk on generative AI was introductory.\",\"There's a desire for Yifan to produce concrete examples for the business.\"]");
        messages.add(asisMessageExampleOutput);

        Message userMessage = new Message();
        userMessage.setRole(Role.user.name());
        userMessage.setContent("Given the input text:\nAs mentioned above, Yifan's has a clear has interest in what's happening in the ML tech space and that is a strength. He should try to identify actionable ways that his insights and ideas can be applied at Audible. Gave a talk on generative AI which was introductory, but it would be great to have Yifan produce concrete examples of how we can apply it to our business.\\nI'd like him to lead a project/epic that involves more design/integration work. Demonstrate more of the end-to-end development skills.\\nGrow knowledge:\\n• Tech depth: focus on deepening his understanding of AWS tech and Amazon systems to keep improving his designs and implementations. Example AWS networking, containers, or other areas we as a team do not have deep knowledge yet.\\n• CRM breadth: Work on additional services. Already doing with ANDS/ACNE. Started on SMDS recently for CloudAuth. Look to gain exposure to the rest of the CRM portfolio.\\nContinue to act as SME for ANDS and ACNE. Think big about how we can improve the system, for instance by improving operability (reduce burden of maintaining it), adding functionality (if expand business need), reducing cost, etc.\\nContinue to provide great support to the rest of the team. This develops leadership skills, forms solid bonds between teammates, and helps to accelerate progress and amplify the other person's abilities. This is a big part of a engineer's role as they advance in level.");
        messages.add(userMessage);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo-16k")
                .messages(messages).build();

        ChatCompletion chatCompletion = openAiClient.createChatCompletion(request);
        System.out.println(chatCompletion);
        System.out.println(chatCompletion.getChoices().get(0) instanceof List);
    }

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
