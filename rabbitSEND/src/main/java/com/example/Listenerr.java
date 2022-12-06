package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listenerr {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange exchange;
    @RabbitListener(queues = "queue.A")
    private void recieve(Student message){
        if (!message.equals(null)){
            template.convertAndSend(exchange.getName(),"routing.C",new Message(1,"salom,keldi"));
        }
        log.info("Queue A->{}",message);
    }

    @RabbitListener(queues = "queue.B")
    private void recieveB(Student message){
        if (!message.equals(null)){
            template.convertAndSend(exchange.getName(),"routing.D",new Message(2,"salom,keldi"));
        }
        log.info("Queue B->{}",message);
    }

    @RabbitListener(queues = "queue.all")
    private void recieveALl(Student message){
        log.info("Queue All->{}",message);
    }
}
