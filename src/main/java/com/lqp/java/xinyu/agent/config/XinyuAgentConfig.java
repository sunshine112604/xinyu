package com.lqp.java.xinyu.agent.config;


import com.lqp.java.xinyu.agent.store.MongoChatMessageStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration//声明这是一个配置类，启用 @Bean
public class XinyuAgentConfig {

    @Autowired
    //注入已有的 Bean（使用别人造好的组件）
    private MongoChatMessageStore mongoChatMessageStore;

    @Bean//声明并注册一个新的 Bean
     //聊天上下文记忆管理（MongoDB + 滑窗）
    public ChatMemoryProvider chatMemoryProviderXinyu(){
        return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMessageStore)
                .build();
    }

    @Autowired
    //向量存储器（如 Pinecone）
    private EmbeddingStore embeddingStore;
    @Autowired
    //文本嵌入向量生成模型
    private EmbeddingModel embeddingModel;
    @Bean
    //用于 RAG 的检索逻辑
    ContentRetriever contentRetrieverXinyuPinecone(){
        return EmbeddingStoreContentRetriever
                .builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(1)
                .minScore(0.7)
                .build();
    }
}
