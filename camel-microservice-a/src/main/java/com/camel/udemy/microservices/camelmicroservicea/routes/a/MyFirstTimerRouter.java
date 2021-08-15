package com.camel.udemy.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.udemy.microservices.camelmicroservicea.beans.CurrentTimeBean;
import com.camel.udemy.microservices.camelmicroservicea.components.SimpleLoggingComponent;

@Component
public class MyFirstTimerRouter extends RouteBuilder{

	@Autowired
	CurrentTimeBean currentTimeBean;
	
	@Autowired
	SimpleLoggingComponent simpleLoggingComponent;
	
	@Override
	public void configure() throws Exception {
		
		from("timer:first-timer") //this is a channel
			.log("${body}")			
			.transform().constant("My ConstantMessage")
			.log("${body}")
			.bean(currentTimeBean,"getCurrentDateTimeFromJapan")
			.log("${body}")
			.bean(simpleLoggingComponent)
			.log("${body}")
		.to("log:first-timer");
	}
	
}
