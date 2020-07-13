package com.dao;


import org.springframework.stereotype.Component;

@Component
public class UserDao {

   private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
