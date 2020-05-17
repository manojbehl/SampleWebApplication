package com.ibm.ignite.controller;

import java.util.Base64;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ignite.dto.AuthUser;
import com.ibm.ignite.dto.UserDto;
import com.ibm.ignite.errorhanding.BusinessException;
import com.ibm.ignite.errorhanding.TechnicalException;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/login")
public class AutheticationController{
	
	@Autowired
	com.ibm.ignite.repository.UserRepo userRepo;

	
	@GetMapping
	public ResponseEntity login() throws BusinessException, TechnicalException {
		
		Object prinicpal =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto  userDetail = new UserDto();
		if(prinicpal instanceof UserDetails) {
			AuthUser authUser = (AuthUser)prinicpal;
			userDetail.setId(authUser.getId());
			userDetail.setEmail(authUser.getUsername());
			userDetail.setFirstName(authUser.getFirstName());
			userDetail.setLastName(authUser.getLastName());
			//userDetail.setDisplayName(authUser.getDisplayName());
			
			/*
			 * for (Iterator iterator = authUser.getAuthorities().iterator();
			 * iterator.hasNext();) { GrantedAuthority type = (GrantedAuthority)
			 * iterator.next(); userDetail.setRole(type.getAuthority()); }
			 */
			String accessToken = generateAuthToken(userDetail);
			userDetail.setAuthToken(accessToken);
			
		}
		
		 return ResponseEntity.ok(userDetail);
	}
	
	private String generateAuthToken(UserDto userDto) {
		String authToken = userDto.getEmail()+ "-" + userDto.getFirstName() + "-" + userDto.getId();

		return Base64.getEncoder().encodeToString(authToken.getBytes());
	}

	
	

}
