package com.myspringboot.webservices.restwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myspringboot.webservices.restwebservices.exceptions.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;
	
	//get all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		
		User user = userService.getUser(id);
		
		if(user==null) {
			throw new UserNotFoundException("not found id-"+id);
		}
		return userService.getUser(id);
	}
	
	//input - details of the user
	//output - created & return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User savedUser = userService.addUser(user);
		
		//create
		///user/{id}  savedUser.getID()
		URI location = ServletUriComponentsBuilder
					   .fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
