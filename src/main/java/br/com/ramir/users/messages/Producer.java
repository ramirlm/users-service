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


    public void produceMsg(String senderMail, String[] recipients, String message){
        Message msg = new Message();
        msg.setSenderEmail(senderMail);
        msg.setRecipients(recipients);
        msg.setMessage(message);

        amqpTemplate.convertAndSend(exchange, routingKey, msg);

        System.out.println("Send message"+message+" to " + recipients.length +" recipients");
    }

}