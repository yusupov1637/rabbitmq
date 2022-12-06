package com.example.controller;

import com.example.model.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    @Autowired
    private RabbitTemplate template;

@Autowired
    private FanoutExchange exchange;



    @PostMapping("/post")
    public String send(@RequestBody Message message){
        template.convertAndSend(exchange.getName(),"",message);
        return "Success";
    }
}
