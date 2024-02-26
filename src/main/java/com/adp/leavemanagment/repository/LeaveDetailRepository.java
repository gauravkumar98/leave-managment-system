package com.adp.leavemanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adp.leavemanagment.modal.LeaveDetails;

public interface LeaveDetailRepository extends JpaRepository<LeaveDetails, String> {
	LeaveDetails findByUserId(String userId);
}
