package com.example.test.microservices.RestFulWebServices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	//Returningn hardcoded message
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	//Returning bean as a Response
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello-World-Bean");
	}
	
	//Getting value from path variable and returning
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(name);
	}
	
	
}

