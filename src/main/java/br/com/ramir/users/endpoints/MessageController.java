package br.com.ramir.users.endpoints;

import br.com.ramir.users.messages.Message;
import br.com.ramir.users.messages.Producer;
import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import br.com.ramir.users.security.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private Producer producer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Credential credential;

    @PostMapping("user/{id}")
    public String message(@PathVariable Long id, @RequestParam(value = "message") String message) {

        if(id == null){
            sendMessageToAdmins(message);
        }else{
            sendMessageToUser(id,message);
        }

        return "Message Sent";
    }

    private void sendMessageToAdmins(String message) {
        Message msg = new Message(message);
        List<User> adminUsers = userRepository.findByAdmin(true);
        String [] recieverMails = new  String[adminUsers.size()];

        for(int i = 0; i < adminUsers.size() ; i++){
            recieverMails[i] = adminUsers.get(i).getEmail();
        }
        msg.setRecipients(recieverMails);
        msg.setSenderEmail(credential.getUser().getEmail());
        producer.produceMsg(msg);
    }

    private void sendMessageToUser(Long id, String message) {
        Message msg = new Message(message);
        User recieverUser = userRepository.findOne(id);

        String[] recieverMail = new String[1];
        recieverMail[0] = recieverUser.getEmail();

        msg.setRecipients(recieverMail);
        msg.setSenderEmail(credential.getUser().getEmail());

        producer.produceMsg(msg);
    }
}