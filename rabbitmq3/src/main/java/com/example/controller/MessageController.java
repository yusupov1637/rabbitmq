package com.example.controller;

import com.example.model.Message;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    @Autowired
    private RabbitTemplate template;

@Autowired
    private HeadersExchange exchange;



    @PostMapping("/post/{message}")
    public String send(@PathVariable(value = "message") String message){
       // template.convertAndSend(exchange.getName(),"routing.A",message);

        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setHeader("color",message);

        MessageConverter converter=new SimpleMessageConverter();

        org.springframework.amqp.core.Message message1=converter.toMessage(message,messageProperties);

        template.send(exchange.getName(),"",message1);
        return "Success";
    }
}
