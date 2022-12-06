package com.example;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange exchange;

    @PostMapping("/post")
    public String post(@RequestBody Student student) {
        template.convertAndSend(exchange.getName(),"routing.A",student);
    return "succes";
    }
}
