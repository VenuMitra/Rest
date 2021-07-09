package com.example.test.microservices.RestFulWebServices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping(path="/users")
	public List<User> getUsers(){
		return service.getUsers();
	}
	
	@GetMapping(path="/user/{id}")
	public User getUsers(@PathVariable int id){
		return service.findOne(id);
	}
	
	@PostMapping(path="/user")
	public void createUser(@RequestBody User user) {
		User savedUser = service.saveUser(user);  
	}

}
