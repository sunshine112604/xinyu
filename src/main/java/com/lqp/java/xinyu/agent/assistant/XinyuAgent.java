package com.lqp.java.xinyu.agent.assistant;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;


@AiService(
        wiringMode = EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXinyu",//配置聊天记忆
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXinyuPinecone"//配置向量存储
)
public interface XinyuAgent {
    //使用提示词
    @SystemMessage(fromResource = "xinyu-prompt-template.txt")
    Flux<String> chat(@MemoryId Long question, @UserMessage String userMessage);
}
