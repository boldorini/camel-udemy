package com.camel.udemy.microservices.camelmicroservicea.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

@Component
public class CurrentTimeBean {
	public String getCurrentDateTime() {
		return "Time now is " + LocalDateTime.now();
	}
	
	public String getCurrentDate() {
		return "Date now is " + LocalDate.now();
	}
	
	public String getCurrentDateTimeFromJapan() {		
		ZoneId tokioDateTime = ZoneId.of("Asia/Tokyo");
		return "Date now is " + LocalDateTime.now(tokioDateTime);
	}

}
