package com.camel.udemy.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//This is a route... is what we want to do with the data we're about 
		//to transfer form one microservice to other.
		//queue - endpoint
		//transformation
		//database - endpoint
		
		//timer
		//transformation
		//log
		from("timer:first-timer")
			.to("log:first-timer");
	}
	
}
