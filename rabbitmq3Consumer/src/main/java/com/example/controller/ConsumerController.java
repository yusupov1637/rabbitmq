package com.example.controller;

import com.example.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ConsumerController {

    @RabbitListener(queues = "queue.A")
    private void recieve(String message){
        log.info("Queue A->{}",message);
    }

    @RabbitListener(queues = "queue.B")
    private void recieveB(String message){
        log.info("Queue B->{}",message);
    }

    @RabbitListener(queues = "queue.all")
    private void recieveALl(String message){
        log.info("Queue All->{}",message);
    }
}
