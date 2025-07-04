package com.lqp.java.xinyu.agent.tools;


import org.apache.commons.text.similarity.CosineDistance;

public class StringSimilarity {
    public static double cosine(String a, String b) {
        CosineDistance cosineDistance = new CosineDistance();
        double distance = cosineDistance.apply(a, b);
        return 1.0 - distance; // 距离 → 相似度
    }
}
