package com.sms.personalInfo.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Student {

	private String studentId;
	private String studentName;
	private String mobileNo;
	private String emailId;
	private CommonsMultipartFile studentAttachmentFile;
	private String studentAttachmentName;
	private String studentAttachmentExt;
	private String updateBy;
	private String message;
	private String messageCode;
	
	
	
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public CommonsMultipartFile getStudentAttachmentFile() {
		return studentAttachmentFile;
	}
	public void setStudentAttachmentFile(CommonsMultipartFile studentAttachmentFile) {
		this.studentAttachmentFile = studentAttachmentFile;
	}
	public String getStudentAttachmentName() {
		return studentAttachmentName;
	}
	public void setStudentAttachmentName(String studentAttachmentName) {
		this.studentAttachmentName = studentAttachmentName;
	}
	public String getStudentAttachmentExt() {
		return studentAttachmentExt;
	}
	public void setStudentAttachmentExt(String studentAttachmentExt) {
		this.studentAttachmentExt = studentAttachmentExt;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
	
	
	
}
