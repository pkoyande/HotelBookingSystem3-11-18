package com.cg.bean;




public class Users {
	
	
	private int userId;
	private String password;
	private String role;
	
	private String userName;
	
	private String mobileNo;
	private String phone;
	private String address;
	private String email;
	
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role="
				+ role + ", userName=" + userName + ", mobileNo=" + mobileNo
				+ ", phone=" + phone + ", address=" + address + ", email="
				+ email + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
