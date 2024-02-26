package com.adp.leavemanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adp.leavemanagment.modal.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, String> {
	LoginUser findByUserId(String userId);
}
