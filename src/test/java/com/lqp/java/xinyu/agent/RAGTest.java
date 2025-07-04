package com.lqp.java.xinyu.agent;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

@SpringBootTest
public class RAGTest{

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    @Test
    public void testLoadMarkdownDocuments() throws IOException {
        // 替换为你本地的 markdown 文件路径
        String[] files = {
                "D:/BaiduNetdiskDownload/java/小智医疗/test/医院信息.md",
                "D:/BaiduNetdiskDownload/java/小智医疗/test/科室信息.md",
                "D:/BaiduNetdiskDownload/java/小智医疗/test/神经内科.md"
        };

        for (String pathStr : files) {
            String content = Files.readString(Path.of(pathStr));
            TextSegment segment = TextSegment.from(content);
            var segments = Collections.singletonList(segment);
            var embeddings = embeddingModel.embedAll(segments).content();
            embeddingStore.addAll(embeddings,segments );
            System.out.println("✅ 成功写入：" + pathStr);
        }

        System.out.println("🎉 所有文档已成功写入向量数据库");
    }
}
