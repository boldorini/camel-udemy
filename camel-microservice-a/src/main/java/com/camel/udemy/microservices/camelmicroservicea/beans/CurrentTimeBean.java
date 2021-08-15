package com.camel.udemy.microservices.camelmicroservicea.beans;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CurrentTimeBean {
	public String getCurrentDateTime() {
		return "Time now is " + LocalDateTime.now();
	}
	

}
