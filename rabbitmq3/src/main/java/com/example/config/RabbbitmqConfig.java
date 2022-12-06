package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbbitmqConfig {

    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";

    @Bean
    Queue queueA(){
        return new Queue("queue.A",false);
    }
    @Bean
    Queue queueB(){
        return new Queue("queue.B",false);
    }

    @Bean
    Queue queueAll(){
        return new Queue("queue.all",false);
    }

    @Bean
    HeadersExchange exchange(){
        return new HeadersExchange("exchange.header");
    }

    @Bean
    Binding binding(Queue queueA,HeadersExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).where("color").matches("red");
    }
    @Bean
    Binding bindingB(Queue queueB,HeadersExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).where("color").matches("blue");
    }

    @Bean
    Binding bindingAll(Queue queueAll,HeadersExchange exchange){
        return BindingBuilder.bind(queueAll).to(exchange).where("color").matches("green");
    }


    @Bean
    MessageConverter  messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template=new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
