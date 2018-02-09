package br.com.ramir.users.messages;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;


    public void produceMsg(Message message){

        amqpTemplate.convertAndSend(exchange, routingKey, message);

        System.out.println("Send message"+message.getMessage()+" to " + message.getRecipients().length +" recipients");
    }

}