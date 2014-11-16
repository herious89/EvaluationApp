package com.example.ptevalform;

public class User {
	private String mFirstName;
	private String mLastName;
	private String mUserName;
	private String mPassword;
	private int mEmployeeID;
	
	public User(String xFirstName, String xLastName, String xUserName, String xPassword, int xEmployeeID) {
		mFirstName = xFirstName;
		mLastName = xLastName;
		mUserName = xUserName;
		mPassword = xPassword;
		mEmployeeID = xEmployeeID;
	}
	
	public void setFirstName(String xFirstName) {
		mFirstName = xFirstName;
	}
	
	public String getFirstName() {
		return mFirstName;
	}
	
	public void setLastName(String xLastName) {
		mLastName = xLastName;
	}
	
	public String getLastName() {
		return mLastName;
	}
	
	public void setUserName(String xUserName) {
		mUserName = xUserName;
	}
	
	public String getUserName() {
		return mUserName;
	}
	
	public void setPassword(String xPassword) {
		mPassword = xPassword;
	}
	
	public String getPassword() {
		return mPassword;
	}
	
	public void setEmployeeID(int xEmployeeID) {
		mEmployeeID = xEmployeeID;
	}
	
	public int getEmployeeID() {
		return mEmployeeID;
	}
	
}
