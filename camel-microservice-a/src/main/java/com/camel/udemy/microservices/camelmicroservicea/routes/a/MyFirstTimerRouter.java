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
		//2021-08-15 18:14:46.177  INFO 3320 --- [r://first-timer] first-timer: Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		//timer and log are keywords. 
		from("timer:first-timer") //this is a channel
			//Transforming the Body Message into something eles using the transform() method
			//.transform().constant("My Constant Message")
			//.transform().constant("Time now is: " +  LocalDateTime.now())
			.bean("currentTimeBean")
		.to("log:first-timer");
	}
	
}
