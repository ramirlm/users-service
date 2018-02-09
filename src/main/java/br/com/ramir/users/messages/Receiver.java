package br.com.ramir.users.messages;


import br.com.ramir.users.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Receiver {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(Message msg) {
        emailService.sendMessage(msg);
    }

}