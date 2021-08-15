# camel-udemy

This Camel project is based on Udemy Classes and is a begining to understand and use this Integration technology.

## Enterprise Integration

Enterprise integration can be tricky since there's a lot of services that need to communicate with themselves. Other complication in this cases is that this services can exchange information in different ways: one can use HTTP protocols, other Messages Queue.

So there's a book called **Enterprise Integration Patterns** that contains the patterns used to make a default between the services communication.

Apache Camel implements theses patterns for us to use it.

### Structure of a Camel Component

``` java
@Component
public class MyFirstTimerRouter extends RouteBuilder{

    @Autowired
    CurrentTimeBean currentTimeBean;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer") //this is a channel
            .transform().constant("The current time is " + LocalDateTime.now()
            // If there's only one method in the bean, we can ommit the method name
            //.bean(currentTimeBean)
            .bean(currentTimeBean,"getCurrentDateTimeFromJapan")
        .to("log:first-timer");
    }
}
```

We extends the class RouteBuiler to tell Camel that this class is a route.
The explanation for the @component and @autowired annotations can be found here:
[@Component](https://www.baeldung.com/spring-component-annotation) and [@Autowired](https://www.baeldung.com/spring-autowire) and more about Spring Bean in [Spring Beans](https://www.baeldung.com/spring-bean).

A Camel route is just a 'pick-and-delivery' structure. Like the diagram:
> Get a info from a queue
> Transform this info into something
> Save the trasnformed info into a database

In the code snippet we got a *timer* and a *log* component that executes something once per second and log something into the console respectively. In between we transform the body of the package using the transform instruction and the bean instruction.

The diference between these two is that transform.constant create a constant value on the begining of the instantiation and use it on each timer iteration. On other hand, the bean method instantiate the bean CurrentTimeBean calling its method 'getCurrentDateTimeFromJapan()'.

There are two thing we can do with a Apache Camel's route: *transformation* and *processing* it. 
When we got a message using the from method, if we DO NOT modify the message's body then we are processing it. So, if we modify the body's content, then we are doing a **transformation** .

### Transformation

The transformation of a route's message occurs when the message's body is modified, we can do this transformation using both *transform* or *bean* methods.

``` java
@Component
public class MyFirstTimerRouter extends RouteBuilder{

    @Autowired
    CurrentTimeBean currentTimeBean;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer") //this is a channel
            .transform().constant("The current time is " + LocalDateTime.now()
            // If there's only one method in the bean, we can ommit the method name
            //.bean(currentTimeBean)
            .bean(currentTimeBean,"getCurrentDateTimeFromJapan")
        .to("log:first-timer");
    }
}
```

### Process

The processing of a route's message occurs when the message's body is NOT modified and we can do this transformation using both *process* or *bean* methods. The process method needs a **Processor** class to do the processing step. The snippet below will show this code:
```java
//The Component class that implements the Processor interface
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

//The route class
package com.camel.udemy.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.udemy.microservices.camelmicroservicea.beans.CurrentTimeBean;
import com.camel.udemy.microservices.camelmicroservicea.components.SimpleLoggingComponent;
import com.camel.udemy.microservices.camelmicroservicea.components.SimpleLoggingProcessor;

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
			.process(new SimpleLoggingProcessor())
			.log("${body}")
		.to("log:first-timer");
	}
	
}

//the log that is presented in the console
2021-08-15 20:33:41.943  INFO 22984 --- [r://first-timer] route1                                   : null
2021-08-15 20:33:41.943  INFO 22984 --- [r://first-timer] route1                                   : My ConstantMessage
2021-08-15 20:33:41.952  INFO 22984 --- [r://first-timer] route1                                   : Date now is 2021-08-16T08:33:41.950700500
2021-08-15 20:33:41.952  INFO 22984 --- [r://first-timer] c.c.u.m.c.c.SimpleLoggingComponent       : Simple Logging Processor Date now is 2021-08-16T08:33:41.950700500
2021-08-15 20:33:41.952  INFO 22984 --- [r://first-timer] route1                                   : Date now is 2021-08-16T08:33:41.950700500
2021-08-15 20:33:41.953  INFO 22984 --- [r://first-timer] c.c.u.m.c.c.SimpleLoggingProcessor       : Simple Logging Component Date now is 2021-08-16T08:33:41.950700500
2021-08-15 20:33:41.953  INFO 22984 --- [r://first-timer] route1                                   : Date now is 2021-08-16T08:33:41.950700500
2021-08-15 20:33:41.953  INFO 22984 --- [r://first-timer] first-timer                              : Exchange[ExchangePattern: InOnly, BodyType: String, Body: Date now is 2021-08-16T08:33:41.950700500]
```