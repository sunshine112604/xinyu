package com.lqp.java.xinyu.agent.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QAExample {
    private String question;      // 用户的问题
    private String groundTruth;   // 标准答案（人工标注）
}
