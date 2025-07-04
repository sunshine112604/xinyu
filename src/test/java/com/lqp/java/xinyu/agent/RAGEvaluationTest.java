package com.lqp.java.xinyu.agent;

import com.lqp.java.xinyu.agent.bean.QAExample;
import com.lqp.java.xinyu.agent.assistant.XinyuAgent;
import com.lqp.java.xinyu.agent.tools.StringSimilarity;
import com.lqp.java.xinyu.agent.tools.TfIdfSimilarity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class RAGEvaluationTest {

    @Autowired
    private XinyuAgent xinyuAgent;

    @Test
    public void testRagEvaluation() {
        List<QAExample> examples = List.of(
//    new QAExample("华西医院是一个什么样的医院？", "四川大学华西医院是中国西部地区综合实力最强的三级甲等医院之一。"),
//    new QAExample("肿瘤科如何治疗患者？", "华西医院肿瘤科采用多学科会诊治疗复杂肿瘤。"),
//    new QAExample("消化内科有什么特色？", "华西医院消化内科擅长胃肠镜、幽门螺杆菌等治疗。"),
//    new QAExample("神经内科主要擅长什么疾病？", "华西医院神经内科擅长脑卒中、癫痫、帕金森等疾病。"),
    new QAExample("四川大学华西医院外科学系有哪些？", "基本外科、骨科、心脏大血管外科、胸外科、泌尿外科、神经外科、血管外科、整形美容烧伤外科、乳腺外科、肝脏外科、麻醉手术中心。")

    // 你可以继续添加更多 QA 对
        );

        for (QAExample example : examples) {
            String generated = xinyuAgent.chat(123L, example.getQuestion())
                                .collectList().block().stream().collect(Collectors.joining());


            double score = TfIdfSimilarity.cosine(generated, example.getGroundTruth());
            System.out.printf("问题：%s\n回答：%s\n得分：%.2f\n\n", example.getQuestion(), generated, score);
        }
    }

}
