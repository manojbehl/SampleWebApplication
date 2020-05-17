package com.ibm.ignite.transformer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ibm.ignite.dto.UserDto;
import com.ibm.ignite.model.User;


@Component
public class DomaintoDtoTransformer {

	public List<UserDto> convertDomainToDto(List<User> listOfUsers){
		 List<UserDto> listOfIUserDtos = new ArrayList<UserDto>();
		UserDto  userDto = null;
		for (Iterator iterator = listOfUsers.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			userDto = new UserDto();
			
			BeanUtils.copyProperties(user, userDto);
			
			listOfIUserDtos.add(userDto);
		}
		
		return listOfIUserDtos;
	}
	
	public UserDto convertDomainToDto(User user){
		UserDto  userDto = new UserDto();
			
		BeanUtils.copyProperties(user, userDto);
			
		
		return userDto;
	}

}
