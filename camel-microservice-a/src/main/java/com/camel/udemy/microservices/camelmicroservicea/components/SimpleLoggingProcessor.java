package com.camel.udemy.microservices.camelmicroservicea.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingProcessor implements Processor{
	
	Logger log = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		//log.info("Simple Logging Component {}", exchange);
		log.info("Simple Logging Component {}", exchange.getMessage().getBody());
		
	}

}
