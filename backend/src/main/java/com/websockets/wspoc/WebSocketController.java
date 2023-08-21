package com.websockets.wspoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chat") // The endpoint to receive messages from clients
    @SendTo("/topic/messages") // The topic to send messages to clients
    public Message handleMessage(Message message) {
        return message; // You can process the message here if needed
    }

    @GetMapping("/sendHello")
    public void sendHelloMessage() {
        String helloMessage = "Hello, everyone!";
        simpMessagingTemplate.convertAndSend("/topic/messages", new Message(helloMessage));
    }
}


