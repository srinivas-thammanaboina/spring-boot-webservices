package com.myspringboot.webservices.restwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

//this should be repository repository
@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	
	private int userCount = 7;
	static {
		userList.add(new User(1,"sravy",new Date()));
		userList.add(new User(2,"rahul",new Date()));
		userList.add(new User(3,"parthu",new Date()));
		userList.add(new User(4,"goutham",new Date()));
		userList.add(new User(5,"subba",new Date()));
		userList.add(new User(6,"divya",new Date()));
		userList.add(new User(7,"meena",new Date()));
	}
	
	public List<User> findAll(){
		return userList;
	}
	
	public User addUser(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
	
	public User getUser(int id) {
		for(User user:userList) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		
		Iterator<User> itr = userList.iterator();
		
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
}
