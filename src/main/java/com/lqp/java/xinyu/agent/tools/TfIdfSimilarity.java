package com.lqp.java.xinyu.agent.tools;

import java.util.*;

public class TfIdfSimilarity {

    public static double cosine(String text1, String text2) {
        Map<String, Integer> freq1 = getFreqMap(text1);
        Map<String, Integer> freq2 = getFreqMap(text2);

        Set<String> allWords = new HashSet<>();
        allWords.addAll(freq1.keySet());
        allWords.addAll(freq2.keySet());

        List<Integer> vec1 = new ArrayList<>();
        List<Integer> vec2 = new ArrayList<>();

        for (String word : allWords) {
            vec1.add(freq1.getOrDefault(word, 0));
            vec2.add(freq2.getOrDefault(word, 0));
        }

        return computeCosine(vec1, vec2);
    }

    private static Map<String, Integer> getFreqMap(String text) {
        Map<String, Integer> freq = new HashMap<>();
        String[] words = text.toLowerCase().replaceAll("[^\\w\\u4e00-\\u9fa5]", "").split("");
        for (String word : words) {
            if (!word.isBlank()) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }
        return freq;
    }

    private static double computeCosine(List<Integer> vec1, List<Integer> vec2) {
        int dot = 0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vec1.size(); i++) {
            int a = vec1.get(i);
            int b = vec2.get(i);
            dot += a * b;
            norm1 += a * a;
            norm2 += b * b;
        }

        return dot / (Math.sqrt(norm1) * Math.sqrt(norm2) + 1e-10);
    }
}
