package com.adp.leavemanagment.service;

import java.util.List;

import com.adp.leavemanagment.exception.domain.UsernameExistException;
import com.adp.leavemanagment.modal.LoginUser;

public interface LoginDetailService {
	
	LoginUser register (String firstName, String lastName, String username,String password) throws UsernameExistException;
	
	List<LoginUser> getAllUser();
	
	LoginUser findUserByUserId(String userId);
	
}
