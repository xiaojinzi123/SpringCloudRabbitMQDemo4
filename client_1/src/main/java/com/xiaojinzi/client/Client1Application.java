package com.xiaojinzi.client;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Client1Application {

    public static void main(String[] args) {
        SpringApplication.run(Client1Application.class, args);
    }

    /*@Bean
    public TopicExchange testExchange() {
        return new TopicExchange("testExchange");
    }

    @Bean
    public Queue testQueue() {
        return new Queue("testQueue1");
    }

    @Bean
    public Binding testBinding(Queue testQueue, TopicExchange testExchange) {
        return BindingBuilder
                .bind(testQueue)
                .to(testExchange)
                .with("*.weather");
    }*/

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @org.springframework.amqp.rabbit.annotation.Queue(
                            name = "testQueue1"
                    ),
                    exchange = @org.springframework.amqp.rabbit.annotation.Exchange(
                            name = "testExchange",
                            type = ExchangeTypes.TOPIC
                    ),
                    key = {"*.weather"}
            )
    )
    public void listen(String in) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("client1 listen = " + in);
    }

}
