package com.lqp.java.xinyu.agent.store;




import com.lqp.java.xinyu.agent.bean.ChatMessages;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

//实现持久化存储
//自定义一个，不然是存在内存中的，现在将他存在数据库中
@Component
public class MongoChatMessageStore implements ChatMemoryStore {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<ChatMessage> getMessages(Object memoryId){
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(criteria);
        //从数据库中查询结果chatMessages

        ChatMessages chatMessages = mongoTemplate.findOne(query, ChatMessages.class);
        if(chatMessages == null ){
            return new LinkedList<>();
        }
        //得到的是存储在 MongoDB 中的 聊天记录字符串（JSON 格式）
        String contentJson = chatMessages.getContent();
        //把这个 JSON 字符串解析为 Java 的 List<ChatMessage> 对象，供 LangChain4j 使用。
        return ChatMessageDeserializer.messagesFromJson(contentJson);
    }

    @Override
    public  void updateMessages(Object memoryId,List<ChatMessage> list){
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(criteria);
        Update update = new Update();
        String string = ChatMessageSerializer.messagesToJson(list);
        update.set("content",string);

        mongoTemplate.upsert(query,update, ChatMessages.class);
    }

    public void deleteMessages(Object memoryId){
         Criteria criteria = Criteria.where("memoryId").is(memoryId);//按照memoryId删除，is（memoryId）是传进来的参数
         Query query = new Query(criteria);
         mongoTemplate.remove(query, ChatMessages.class);
    }

}
