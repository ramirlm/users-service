package br.com.ramir.users.service;

import br.com.ramir.users.exceptions.MessageNotSentException;
import br.com.ramir.users.messages.Message;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    public Boolean sendMessage(Message msg) throws MessageNotSentException {
        return true;
    }
}
