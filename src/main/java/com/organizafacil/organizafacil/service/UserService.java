package com.organizafacil.organizafacil.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.organizafacil.organizafacil.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}