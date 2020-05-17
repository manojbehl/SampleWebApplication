package com.ibm.ignite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ignite.dto.UserDto;
import com.ibm.ignite.model.User;
import com.ibm.ignite.repository.UserRepo;
import com.ibm.ignite.transformer.DomaintoDtoTransformer;

@Service
public class UserServiceImpl {

	@Autowired
	UserRepo userRepository;
	
	@Autowired
	DomaintoDtoTransformer domaintoDtoTransformer;
	
	public Iterable<UserDto> getAllUsers() {
		Iterable<User> iterable= this.userRepository.findAll();

		List<User> userList = new ArrayList<User>();
		iterable.forEach(e -> userList.add(e));

		
		return domaintoDtoTransformer.convertDomainToDto(userList);
	}

	public User create(User user) {
		
		return this.userRepository.save(user);
	}

	public void update(User user) {
		this.userRepository.save(user);
	}
}
