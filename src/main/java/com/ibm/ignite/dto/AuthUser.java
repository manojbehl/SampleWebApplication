package com.ibm.ignite.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthUser extends  User{

	private long id;

	
	private String firstName;
	private String lastName;

	
	public  AuthUser(long id, String userName,String password, String firstName,String lastName, Collection<? extends GrantedAuthority> authorities) {
		super(userName,password , authorities);
		this.id= id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	


}
