package com.example.test.microservices.RestFulWebServices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int usersCount=3;
	
	static {
		users.add(new User(1, "Venu", new Date()));
		users.add(new User(2, "Pradeep", new Date()));
		users.add(new User(3, "Lakshmi", new Date()));
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		return users.stream().filter(users->users.getId()==id).findAny().orElse(null);
	}

	
}
