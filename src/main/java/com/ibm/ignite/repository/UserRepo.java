package com.ibm.ignite.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.ignite.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	
	public User findByEmail(String email);

}
