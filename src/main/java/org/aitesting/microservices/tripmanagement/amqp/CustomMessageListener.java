package org.aitesting.microservices.tripmanagement.amqp;

import org.aitesting.microservices.passengermanagement.amqp.CustomMessageTrip;
import org.aitesting.microservices.tripmanagement.TripManagementApplication;
import org.aitesting.microservices.tripmanagement.models.Trip;
import org.aitesting.microservices.tripmanagement.repositories.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);
    
    @Autowired
    private TripRepository tripRepository;

//    @RabbitListener(queues = TripManagementApplication.QUEUE_GENERIC_NAME)
//    public void receiveMessage(final Message message) {
//        log.info("Received message as generic: {}", message.toString());
//    }

    @RabbitListener(queues = TripManagementApplication.TRIP_MANAGEMENT_QUEUE)
    public void receiveMessage(final CustomMessageTrip customMessage) {
    	Trip obj = new Trip();
    	obj.setIdpassenger(customMessage.getIdpassenger());
    	obj.setOriginAddress(customMessage.getOriginAddress());
    	obj.setDestinationAddress(customMessage.getDestinationAddress());
    	tripRepository.save(obj);
        log.info("Received Trip message as specific class: {}", customMessage.toString());
    }
}
