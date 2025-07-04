package com.lqp.java.xinyu.agent.config;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;

import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;

import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddingStoreConfig {

    @Autowired
    private EmbeddingModel embeddingModel;

    //
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        PineconeEmbeddingStore embeddingStore = PineconeEmbeddingStore.builder()
                .apiKey("pcsk_6gRgd2_4F4yVJo2F8HzWkK8d6tAwXnVi3H5bQN3F5Pki3bZdj3pF875bj7P1xdc6CGf2dp")
                .index("xinyu-index")
                .nameSpace("xinyu-namespace")
                .createIndex(PineconeServerlessIndexConfig.builder()
                        .cloud("AWS")
                        .region("us-east-1")
                        .dimension(embeddingModel.dimension())
                        .build())
                .build();
        return embeddingStore;
    }

//    @Bean
//    public EmbeddingStore<TextSegment> embeddingStore() {
//        return QdrantEmbeddingStore.builder()
//                .host("783a998d-ba97-499c-8450-fe742f2ade38.europe-west3-0.gcp.cloud.qdrant.io")
//                .port(6334) // Qdrant Cloud 默认 gRPC 端口
//                .apiKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2Nlc3MiOiJtIn0.nuAfu31hNbnx0JYqJVQgmivIrTcEqTCQ53m-3NAvzgU")
//                .collectionName("xinyu-collection")
//                .build();
//    }

}
