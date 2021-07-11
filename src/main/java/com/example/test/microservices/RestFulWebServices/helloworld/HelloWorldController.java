package com.example.test.microservices.RestFulWebServices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
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
	
	//for internationalization
	@GetMapping(path = "/hello-world-internationalizaed")
	public String helloWorldinternationalized(@RequestHeader(name = "Accept-Language",required = false)Locale locale) {
		return messageSource.getMessage("simple.message", null, "Defalut Message", locale);
	}
	
	//for internationalization
	@GetMapping(path = "/hello-world-internationalizaed-simplified")
	public String helloWorldinternationalized() {
		return messageSource.getMessage("simple.message", null, "Defalut Message", LocaleContextHolder.getLocale());
	}
	
}

