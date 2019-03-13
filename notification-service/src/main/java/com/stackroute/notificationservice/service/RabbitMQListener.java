package com.stackroute.notificationservice.service;

import com.stackroute.notificationservice.domain.Documents;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQListener {

     private final SimpMessagingTemplate send;

    @Autowired
    RabbitMQListener(SimpMessagingTemplate send) {
        this.send = send;
    }

     @RabbitListener(queues = "${javainuse6.rabbitmq.queue}", containerFactory = "jsaFactory")
    public void consume(Documents documents){
        System.out.println("Recieved Message From RabbitMQ: " + documents.toString());
        this.send.convertAndSend("/topic", documents);
    }

}