/**
 * @author dingli
 * @date 2018年9月18日
 */
package com.tv189.kafka.sender;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tv189.kafka.Message;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息发送
 *
 * @author dingli02
 * @date 2018/09/18 16:20
 */
@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", message.toString());
        kafkaTemplate.send("zhisheng", message.toString());
    }
}
