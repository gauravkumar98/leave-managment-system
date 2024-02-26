package com.adp.leavemanagment.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adp.leavemanagment.exception.domain.UsernameExistException;
import com.adp.leavemanagment.modal.LoginUser;
import com.adp.leavemanagment.modal.UserPrincipal;
import com.adp.leavemanagment.repository.LoginUserRepository;
import com.adp.leavemanagment.service.LoginDetailService;


@Service
@javax.transaction.Transactional
@Qualifier("userDetailService")
public class LoginDetailServiceImpl implements LoginDetailService,UserDetailsService{
	
	private LoginUserRepository loginUserRepository;
	private Logger logger = LoggerFactory.getLogger(LoginDetailServiceImpl.class); 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public LoginDetailServiceImpl(LoginUserRepository loginUserRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.loginUserRepository = loginUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}



	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LoginUser user = loginUserRepository.findByUserId(userId);
		if(user == null) {
			logger.error("User not found by userId:" + userId);
			throw new UsernameNotFoundException("User not found by userId:" + userId);
		} else {
			user.setLastLoginDate(new Date());
			loginUserRepository.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			logger.info("User found and returning");
			return userPrincipal;
		}
	}



	@Override
	public LoginUser register(String firstName, String lastName, String userId,String password) throws UsernameExistException {
		if(isUserIDExist(userId)) {
			throw new UsernameExistException("User already present with userId: " + userId);
		} else {
			String encyrptPassword = passwordEncoder(password);
			LoginUser user = new LoginUser(userId, firstName, lastName, encyrptPassword);
			LoginUser savedUser = loginUserRepository.save(user);
			logger.info("user created");
			return savedUser;
		}
	}



	@Override
	public List<LoginUser> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public LoginUser findUserByUserId(String userId) {
		return loginUserRepository.findByUserId(userId);
	}
	
	private boolean isUserIDExist(String userId) {
		if(StringUtils.isNotBlank(userId)) {
			LoginUser currentUser = findUserByUserId(userId);
			if(currentUser != null) {
				return true;
			}
		}
		return false;
	}
	
	private String passwordEncoder(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

}
