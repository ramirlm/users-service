package br.com.ramir.users.endpoints;

import br.com.ramir.users.messages.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    Producer producer;

    @PostMapping("user/{id}")
    public String message(@PathVariable Long id, @RequestParam(value = "message") String message) {
        if(id == null)
            producer.produceMsgToAdmins(message);

        return "Message Sent";
    }
}