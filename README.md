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


