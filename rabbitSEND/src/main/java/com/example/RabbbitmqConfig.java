package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbbitmqConfig {

    public static final String ROUTING_A = "routing.C";
    public static final String ROUTING_B = "routing.D";

    @Bean
    Queue queueA(){
        return new Queue("queue.C",false);
    }
    @Bean
    Queue queueB(){
        return new Queue("queue.D",false);
    }

    @Bean
    Queue queueAll(){
        return new Queue("queue.alll",false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange.topic");
    }

    @Bean
    Binding binding(Queue queueA,TopicExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }
    @Bean
    Binding bindingB(Queue queueB,TopicExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
    }

    @Bean
    Binding bindingAll(Queue queueAll,TopicExchange exchange){
        return BindingBuilder.bind(queueAll).to(exchange).with("routing.**");
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
