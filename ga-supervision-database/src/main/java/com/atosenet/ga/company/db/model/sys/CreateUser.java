package com.atosenet.ga.company.db.model.sys;

public class CreateUser {
	private String account;
	
	private String userName;
	private String userUserName;
	private String email;
	private String companyId;
	
	private String gongsiId;
	
	
	private String companyType;
	private String userRole;
	private String companyName;
	public String getGongsiId() {
		return gongsiId;
	}
	public void setGongsiId(String gongsiId) {
		this.gongsiId = gongsiId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserUserName() {
		return userUserName;
	}
	public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "CreateUser [account=" + account + ", userName=" + userName + ", userUserName=" + userUserName
				+ ", email=" + email + ", companyId=" + companyId + ", gongsiId=" + gongsiId + ", companyType="
				+ companyType + ", userRole=" + userRole + ", companyName=" + companyName + "]";
	}
	
	
	
	
	
}
