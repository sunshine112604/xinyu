package com.lqp.java.xinyu.agent.controller;

import com.lqp.java.xinyu.agent.assistant.XinyuAgent;
import com.lqp.java.xinyu.agent.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/xinyu")
@Tag(name = "心语")
public class XinyuAgentController {

    @Autowired
    private XinyuAgent xinyuAgent;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/event-stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return xinyuAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
