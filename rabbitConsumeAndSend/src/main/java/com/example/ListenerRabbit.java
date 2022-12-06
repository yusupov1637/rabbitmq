package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ListenerRabbit {
    @RabbitListener(queues = "queue.C")
    private void recieve(Message message){
        log.info("Queue C->{}",message);
    }

    @RabbitListener(queues = "queue.D")
    private void recieveB(Message message){
        log.info("Queue D->{}",message);
    }

    @RabbitListener(queues = "queue.alll")
    private void recieveALl(Message message){
        log.info("Queue Alll->{}",message);
    }

}
