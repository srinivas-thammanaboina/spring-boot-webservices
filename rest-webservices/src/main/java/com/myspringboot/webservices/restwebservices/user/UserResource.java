package com.myspringboot.webservices.restwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Resource<User> getUser(@PathVariable int id) {
		
		User user = userService.getUser(id);
		
		if(user==null) {
			throw new UserNotFoundException("not found id-"+id);
		}
		//input - details of the user
		//output - created & return the created URI
		
		// HATEOAS
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
		
	}
	
	
	// @valid - java validation api having its default implementation
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = userService.addUser(user);
		
		//create
		///user/{id}  savedUser.getID()
		URI location = ServletUriComponentsBuilder
					   .fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//input - details of the user
		//output - created & return the created URI
		@DeleteMapping("/users/{id}")
		public User deleteUser(@PathVariable int id) {
			User user = userService.deleteUser(id);
			
			if(user==null) {
				throw new UserNotFoundException("id - "+id);
			}
			
			return user;
		}
		
}
