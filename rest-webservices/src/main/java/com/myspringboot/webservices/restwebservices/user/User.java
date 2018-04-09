package com.myspringboot.webservices.restwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User{

	private Integer id;
	
	//@valid in controller will take care f these validations
	@Size(min=2,message="Name should have atlease 2 characters")
	private String name;
	@Past
	private Date birthday;
	
	protected User() {
		
	}
	public User(Integer id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}
	
	
	
}
