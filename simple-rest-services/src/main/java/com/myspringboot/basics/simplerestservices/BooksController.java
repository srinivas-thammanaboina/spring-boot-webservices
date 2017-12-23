package com.myspringboot.basics.simplerestservices;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

	@GetMapping("/books")
	public List<Books> getAllBooks(){
		return Arrays.asList(new Books(21,"operating computing systems","IOS"),new Books(12,"mathematics","Ramanujan"));
	}
}
