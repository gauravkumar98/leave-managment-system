package com.adp.leavemanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.leavemanagment.constant.SecurityConstant;
import com.adp.leavemanagment.exception.ExceptionHandling;
import com.adp.leavemanagment.exception.domain.UsernameExistException;
import com.adp.leavemanagment.modal.LoginUser;
import com.adp.leavemanagment.modal.UserPrincipal;
import com.adp.leavemanagment.service.LoginDetailService;
import com.adp.leavemanagment.utility.JwtTokenProvider;

@RestController
public class BasicController extends ExceptionHandling {

	private LoginDetailService loginDetailService;
	private JwtTokenProvider jwtTokenProvider;
	private AuthenticationManager authenticationManager;

	@Autowired
	public BasicController(LoginDetailService loginDetailService, JwtTokenProvider jwtTokenProvider,
			AuthenticationManager authenticationManager) {
		this.loginDetailService = loginDetailService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginUser> login(@RequestBody LoginUser loginUser) throws UsernameExistException {
		authenticate(loginUser.getUserId(), loginUser.getPassword());
		LoginUser user = loginDetailService.findUserByUserId(loginUser.getUserId());
		UserPrincipal userPrincipal = new UserPrincipal(user);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(user, jwtHeader, HttpStatus.OK);
	}

	
	@PostMapping("/register")
	public ResponseEntity<LoginUser> register(@RequestBody LoginUser loginUser) throws UsernameExistException {
		LoginUser newUser = loginDetailService.register(loginUser.getFirstName(), loginUser.getLastName(),
				loginUser.getUserId(), loginUser.getPassword());
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
		return headers;
	}

	private void authenticate(String userId, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
	}

}
