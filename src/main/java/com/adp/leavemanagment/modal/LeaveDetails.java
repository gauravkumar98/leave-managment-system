package com.adp.leavemanagment.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class LeaveDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false,updatable = false)
	private String userId;

	@Column(nullable = false)
	private Date startDate;

	@Column(nullable = false)
	private Date endDate;

	@Column(nullable = false)
	private String leaveType;

	private String reason;

	@Column(nullable = false)
	private String transactionType;

	private boolean acceptRejectFlag;

	@Column(nullable = false)
	private String year;

	@Column(nullable = false)
	private Date createdDate;

	@Column(nullable = false)
	private Date modifiedDate;

	public LeaveDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveDetails(String userId, Date startDate, Date endDate, String leaveType, String reason,
			String transactionType, boolean acceptRejectFlag, String year) {
		super();
		Date todayDate = new Date();
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.reason = reason;
		this.transactionType = transactionType;
		this.acceptRejectFlag = acceptRejectFlag;
		this.year = year;
		this.createdDate = todayDate;
		this.modifiedDate = todayDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public boolean isAcceptRejectFlag() {
		return acceptRejectFlag;
	}

	public void setAcceptRejectFlag(boolean acceptRejectFlag) {
		this.acceptRejectFlag = acceptRejectFlag;
	}

	public String isYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "LeaveDetails [id=" + id + ", userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", leaveType=" + leaveType + ", reason=" + reason + ", transactionType=" + transactionType
				+ ", acceptRejectFlag=" + acceptRejectFlag + ", year=" + year + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
