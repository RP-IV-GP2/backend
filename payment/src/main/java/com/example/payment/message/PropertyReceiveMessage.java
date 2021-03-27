package com.example.payment.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.payment.model.Property;
import com.example.payment.repository.PropertyRepository;

@Component
public class PropertyReceiveMessage {
	
	private PropertyRepository propertyRepository;
	
	@Autowired
	public PropertyReceiveMessage(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload Property property) {
		propertyRepository.save(property);
	}
}
