package com.ibm.ignite.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ibm.ignite.service.MyUserDetailService;

@Component
public class AutheticationInterceptor implements HandlerInterceptor {

	private static String AUTH_HEADER = "Authorization";
	
	private static String AUTH_TOKEN = "authToken";
	
	private static final String JSON_HAL_CONTENT_TYPE = "application/hal+json";

	private static final String UNAUTHORISED_ACCESS_MSG = "{\"error\":true,\"message\":\"It seems that your credentials are invalid. Please login again with correct credentials.\" }";

	private static final String TOKEN_EXPIRE_MSG = "{\"error\":true,\"message\":\"Oops, it seems that your session has expired. Kindly login again.\" }";

	@Autowired
	AuthenticationManager autheticationManager;

	@Autowired
	MyUserDetailService myUserDetailService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		  System.err.println("URI -- "+request.getRequestURL());
		  if(request.getRequestURI()!=null &&
		  (request.getRequestURI().contains("/h2-console"))) return true;
		  
		  
		  String authHeader = request.getParameter(AUTH_TOKEN);
		  
//		  System.err.println("Attribute"+ request.getAttribute(AUTH_TOKEN));
//		  System.err.println("Paramtere"+ request.getParameter(AUTH_TOKEN));
		  
		  if (authHeader == null) { 
			 response.setContentType(JSON_HAL_CONTENT_TYPE);
			 response.getOutputStream().write(TOKEN_EXPIRE_MSG.getBytes()); return false;
		  }
		  String strValue = "";
		  try
		  { 
			  strValue = new String(java.util.Base64.getDecoder().decode(authHeader.getBytes(
					  		)), "UTF-8"); 
		  }
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
			  // TODO: handle exception return false; 
		  }
		  
		  String[] arrayOfString = strValue.split("\\:");
		  
		  System.err.println(arrayOfString);
		  
		  if (arrayOfString.length > 1) 
		  { 
			  	String userName = arrayOfString[0]; String
			  	password = arrayOfString[1]; System.err.println(userName);
			  	System.err.println(password);
		  
			  	password = Base64.getEncoder().encodeToString((password + "").getBytes("utf-8")); 
			  	UsernamePasswordAuthenticationToken
			  	usernamePasswordAuthenticationToken = new
			  	UsernamePasswordAuthenticationToken( userName, password);
		  
			  	Authentication authentication = null; 
			  	try { authentication =
			  			autheticationManager.authenticate(usernamePasswordAuthenticationToken);
		  
		  } catch (Exception ex) { 
			  ex.printStackTrace();
			  response.setContentType(JSON_HAL_CONTENT_TYPE);
			  response.getOutputStream().write(UNAUTHORISED_ACCESS_MSG.getBytes());
		  
			  return false; 
		  } 
		 if (authentication.isAuthenticated()) {
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			 return true; 
		 } else { 
			
			response.setContentType(JSON_HAL_CONTENT_TYPE);
			  response.getOutputStream().write(UNAUTHORISED_ACCESS_MSG.getBytes()); 
			  return false; 
			 } 
		 } else { 
			 //String encodedValue = authHeader.split("Basic ")[1];
		  
			 if (isValidToken(authHeader)) return true;
		  
		  	response.setContentType(JSON_HAL_CONTENT_TYPE);
		  	response.getOutputStream().write(TOKEN_EXPIRE_MSG.getBytes()); return false;
		  }
		  
		 
		
	}

	private boolean isValidToken(String encodedValue) {
		String authToken = "";

		try {
			authToken = new String(Base64.getDecoder().decode(encodedValue.getBytes()));
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		String[] strArray = authToken.split("\\-");
		if (strArray.length <= 0)
			return false;

		UserDetails userDetails = (UserDetails) myUserDetailService.loadUserByUsername(strArray[0]);
		if (userDetails != null) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
					new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(token);
			return true;
		}

		return false;
	}
	

	public static void main(String[] args) throws UnsupportedEncodingException {
		String encodedValue = "Test";
		String value = Base64.getEncoder().encodeToString((encodedValue + "").getBytes("utf-8"));
		System.err.println(value);

	}

}
