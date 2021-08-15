package com.camel.udemy.microservices.camelmicroservicea.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleLoggingComponent {
	
	private Logger log = LoggerFactory.getLogger(SimpleLoggingComponent.class);
	
	public void process(String message) {
		log.info("Simple Logging Component {}", message);
	}

}
