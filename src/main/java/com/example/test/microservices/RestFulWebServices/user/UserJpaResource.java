package com.example.test.microservices.RestFulWebServices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.test.microservices.RestFulWebServices.user.exception.NoUsersFoundExcpetion;
import com.example.test.microservices.RestFulWebServices.user.exception.UserNotFoundException;

import net.bytebuddy.asm.Advice.This;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping(path = "/jpa/users")
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		if(users.isEmpty())
			throw new NoUsersFoundExcpetion("Users are Empty");
		return users;
	}

	@GetMapping(path = "/jpa/user/{id}")
	public EntityModel<User> getUsers(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User id:"+id);
		
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
		model.add(linkToUsers.withRel("all-users"));
			return  model;
	}

	//Returning response for the creation of user and also sending location of created user
	@PostMapping(path = "/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/jpa/user/{id}")
	public void delteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(path="/jpa/user/{id}/posts")
	public List<Post> retriveAllPosts(@PathVariable int id) {
		Optional<User> users = userRepository.findById(id);
		if(!users.isPresent())
			throw new UserNotFoundException("Id--"+ id);
		
		return users.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/user/{id}/post")
	public ResponseEntity<Object> createUserPost(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> savedUser = userRepository.findById(id);
		
		if(!savedUser.isPresent())
			throw new UserNotFoundException("Id--"+ id);
		
		post.setUser(savedUser.get());
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
