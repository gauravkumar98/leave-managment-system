package com.adp.leavemanagment.utility;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.adp.leavemanagment.constant.SecurityConstant;
import com.adp.leavemanagment.modal.UserPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateJwtToken (UserPrincipal userPrincipal) {
		Date expireDate = new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME);
		
		
		return JWT.create().withIssuer(SecurityConstant.GET_ARRAYS_LLC)
				.withAudience(SecurityConstant.GET_ARRAYS_ADMINISTRATION)
				.withIssuedAt(new Date())
				.withSubject(userPrincipal.getUsername())
				.withExpiresAt(expireDate)
				.sign(Algorithm.HMAC256(secret.getBytes()));
	}
	
	public Authentication getAuthentication(String username, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new 
				UsernamePasswordAuthenticationToken(username, null);
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return usernamePasswordAuthenticationToken;
	}
	
	public boolean isTokenValid(String username , String token) {
		JWTVerifier verifier = getJWTVerifier();
		return StringUtils.isNotEmpty(username)&& !isTokenExpired(verifier,token);
		
	}
	
	public String getSubject(String token) {
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getSubject();
	}
	
	
	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expireDate = new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME);
		return expireDate.before(new Date());
	}

	private JWTVerifier getJWTVerifier() {
		JWTVerifier jwtVerifier = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
			jwtVerifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_ARRAYS_LLC).build();
		} catch (Exception e) {
			System.out.println(e);
		}
		return jwtVerifier;
	}

}
