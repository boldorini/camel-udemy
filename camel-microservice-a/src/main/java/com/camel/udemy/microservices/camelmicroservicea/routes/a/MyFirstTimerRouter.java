package com.camel.udemy.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.udemy.microservices.camelmicroservicea.beans.CurrentTimeBean;

@Component
public class MyFirstTimerRouter extends RouteBuilder{

	@Autowired
	CurrentTimeBean currentTimeBean;
	
	@Override
	public void configure() throws Exception {
		
		from("timer:first-timer") //this is a channel
			.bean(currentTimeBean,"getCurrentDateTimeFromJapan")
		.to("log:first-timer");
	}
	
}
