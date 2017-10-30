package org.aitesting.microservices.tripmanagement.amqp;

import org.aitesting.microservices.tripmanagement.TripmanagementApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageSender {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CustomMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000L)
    public void sendMessageDriver() {
    	CustomMessageDriver customMessage = new CustomMessageDriver();
        log.info("Sending Driver message...");
        rabbitTemplate.convertAndSend(TripmanagementApplication.EXCHANGE_NAME, TripmanagementApplication.DRIVER_ROUTING_KEY, customMessage);
    }

    @Scheduled(fixedDelay = 5000L)
    public void sendMessagePassenger() {
    	CustomMessagePassenger customMessage = new CustomMessagePassenger();
        log.info("Sending Passenger message...");
        rabbitTemplate.convertAndSend(TripmanagementApplication.EXCHANGE_NAME, TripmanagementApplication.PASSENGER_ROUTING_KEY, customMessage);
    }
}
