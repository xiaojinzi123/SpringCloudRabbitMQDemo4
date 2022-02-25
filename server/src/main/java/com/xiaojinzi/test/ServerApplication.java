package com.xiaojinzi.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @ResponseBody
    @RequestMapping("pushMessage")
    public String pushMessage() {
        for (int i = 1; i <= 10; i++) {
            // amqpTemplate.convertAndSend("testQueue", "testData____" + i);
            // amqpTemplate.convertAndSend("testExchange", "key1", "testData____" + i);
        }
        amqpTemplate.convertAndSend("testExchange", "china.weather", "china 天气很好");
        amqpTemplate.convertAndSend("testExchange", "Japan.weather", "Japan 天气很差");
        amqpTemplate.convertAndSend("testExchange", "china.age", "china 的年龄 110");
        return "hello, convertAndSend 'testData' success ";
    }

}