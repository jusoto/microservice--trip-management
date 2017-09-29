package org.aitesting.microservices.tripmanagement;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class TripManagementApplication implements RabbitListenerConfigurer {

	public static final String EXCHANGE_NAME = "appExchange";
	public static final String QUEUE_GENERIC_NAME = "genericQueue";
	public static final String PASSENGER_MANAGEMENT_QUEUE = "passengerManagementQueue";
	public static final String DRIVER_MANAGEMENT_QUEUE = "driverManagementQueue";
	public static final String TRIP_MANAGEMENT_QUEUE = "tripManagementQueue";
	public static final String GENERIC_ROUTING_KEY = "generic.key";
	public static final String DRIVER_ROUTING_KEY = "driver.key";
	public static final String PASSENGER_ROUTING_KEY = "passenger.key";
	public static final String TRIP_ROUTING_KEY = "trip.key";
	
	// Test values to send different messages to DriverManagement & PassengerManagement Micro Services
	public static int messageDriverStatusTest = 2;
	public static int messagePassengerStatusTest = 2;
	
	@Bean
	public TopicExchange appExchange() {
	    return new TopicExchange(EXCHANGE_NAME);
	}
	 
	@Bean
	public Queue appQueueGeneric() {
	    return new Queue(QUEUE_GENERIC_NAME);
	}
	 
	@Bean
	public Queue appDriverManagementQueue() {
	    return new Queue(DRIVER_MANAGEMENT_QUEUE);
	}
	 
	@Bean
	public Queue appPassengerManagementQueue() {
	    return new Queue(PASSENGER_MANAGEMENT_QUEUE);
	}
	 
	@Bean
	public Queue appTripManagementQueue() {
	    return new Queue(TRIP_MANAGEMENT_QUEUE);
	}
	 
	@Bean
	public Binding declareBindingGeneric() {
	    return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with(GENERIC_ROUTING_KEY);
	}
	 
	@Bean
	public Binding declareBindingDriverManagement() {
	    return BindingBuilder.bind(appDriverManagementQueue()).to(appExchange()).with(DRIVER_ROUTING_KEY);
	}
	 
	@Bean
	public Binding declareBindingPassengerManagement() {
	    return BindingBuilder.bind(appPassengerManagementQueue()).to(appExchange()).with(PASSENGER_ROUTING_KEY);
	}
	 
	@Bean
	public Binding declareBindingTripManagement() {
	    return BindingBuilder.bind(appTripManagementQueue()).to(appExchange()).with(TRIP_ROUTING_KEY);
	}
	
	// You can comment all methods below and remove interface's implementation to use the default serialization / deserialization
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
	
    @Bean
    public FilterRegistrationBean commonsRequestLoggingFilter()
    {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CORSFilter());
        return registrationBean;
    }

	public static void main(String[] args) {
		SpringApplication.run(TripManagementApplication.class, args);
	}
}
